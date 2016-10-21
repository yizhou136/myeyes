package com.example.amqp;

import com.rabbitmq.client.*;
import org.springframework.amqp.core.MessageDeliveryMode;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeoutException;

/**
 * Created by zhougb on 2016/8/29.
 */
public class AmqpProducer{
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss", Locale.CHINA);
    public static String generateMsg(){
        return String.format("haha %s", simpleDateFormat.format(new Date()));
    }

    public static void main2(String args[]) throws Exception {
        //rpcClient();
        t1();
    }

    public static Channel generateChannel()throws Exception{
        ConnectionFactory  connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.5.203");
        connectionFactory.setPort(5670);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        return channel;
    }

    public static void rpcClient()throws Exception{
        Channel channel = generateChannel();
        AMQP.Queue.DeclareOk declareOk = channel.queueDeclare();

        channel.exchangeDeclare(TestAmqp.PingExchangerName, "direct", true, false, null);

        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
        builder.replyTo(declareOk.getQueue());
        AMQP.BasicProperties basicProperties = builder.build();
        channel.basicPublish(TestAmqp.PingExchangerName, "ping", basicProperties, "adfasdf".getBytes());

        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        channel.basicConsume(declareOk.getQueue(), queueingConsumer);
        while (true){
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            System.out.println(new String(delivery.getBody()));

            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }

    public static void t1() throws Exception{
        Channel channel = generateChannel();
        //channel.exchangeDeclare(TestAmqp.ExchangerName, "direct", true, false, null);
        //channel.exchangeDeclare(TestAmqp.FanoutExchangerName, "fanout", true, false, null);
        channel.exchangeDeclare(TestAmqp.TopicExchangerName, "topic", false, false, null);

        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(String.format("replyCode:%d, replyText:%s, exchange:%s, routingKey:%s, properties, body:%s",
                        replyCode, replyText, exchange, routingKey, new String(body)));
            }
        });
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println(String.format("handleAck deliveryTag:%d multiple:%s",
                        deliveryTag, String.valueOf(multiple)));
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println(String.format("handleNack deliveryTag:%d multiple:%s",
                        deliveryTag, String.valueOf(multiple)));
            }
        });
        AMQP.Confirm.SelectOk selectOk = channel.confirmSelect();

        //channel.queueDeclare(TestAmqp.QueueName, false, false, false, null);
        System.out.println("start send msg  selectOk:"+selectOk);

        //channel.basicPublish(TestAmqp.FanoutExchangerName, TestAmqp.RouteingName, basicProperties, generateMsg().getBytes());
        //channel.basicPublish(TestAmqp.TopicExchangerName, TestAmqp.RouteingName, basicProperties, generateMsg().getBytes());
        //channel.basicPublish(TestAmqp.TopicExchangerName, "", basicProperties, generateMsg().getBytes());

        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
        //basicProperties = basicProperties.builder().deliveryMode(MessageDeliveryMode.toInt(MessageDeliveryMode.PERSISTENT)).build();
        //builder.deliveryMode(MessageDeliveryMode.toInt(MessageDeliveryMode.PERSISTENT));

        AMQP.BasicProperties basicProperties = builder.build();
        //channel.basicPublish(TestAmqp.TopicExchangerName, "haha", true, basicProperties, generateMsg().getBytes());
        //channel.basicPublish(TestAmqp.ExchangerName, "haha", true, basicProperties, generateMsg().getBytes());
        //channel.basicPublish(TestAmqp.ExchangerName, "haha2", true, basicProperties, generateMsg().getBytes());
        channel.basicPublish(TestAmqp.TopicExchangerName, "haha", basicProperties, generateMsg().getBytes());
        System.out.println("start send msg end");

        //Thread.sleep(3330);
        //channel.re
        channel.waitForConfirms();
        channel.close();
        //connection.close();
    }
}
