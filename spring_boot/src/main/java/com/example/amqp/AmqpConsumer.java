package com.example.amqp;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by zhougb on 2016/8/29.
 */
public class AmqpConsumer {

    public static void main2(String args[]) throws Exception {
        while (true)
            try {
                t1();
            }catch (Exception e){e.printStackTrace();}
    }

    public static void t3() throws Exception{
        Channel channel = generateChannel();
        channel.exchangeDeclare(TestAmqp.PingExchangerName, "direct", true, false, null);
        channel.queueDeclare("rpc", false, false, false, null);
        //channel.queueBind(TestAmqp.TopicQueueName, TestAmqp.TopicExchangerName, TestAmqp.RouteingName);
        //channel.queueBind(TestAmqp.TopicQueueName, TestAmqp.TopicExchangerName, "*");
        channel.queueBind("rpc", TestAmqp.PingExchangerName, "ping");

        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        channel.basicConsume("rpc", false, queueingConsumer);

        while (true){
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            System.out.println("log: "+new String(delivery.getBody()));
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);

            System.out.println("reply to :"+delivery.getProperties().getReplyTo());
            channel.basicPublish("", delivery.getProperties().getReplyTo(), null, "asfasdfas".getBytes());
        }
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

    public static void t2() throws  Exception{
        Channel channel = generateChannel();
        AMQP.Queue.DeclareOk declareOk1 = channel.queueDeclare();
        AMQP.Queue.DeclareOk declareOk2 = channel.queueDeclare();
        AMQP.Queue.DeclareOk declareOk3 = channel.queueDeclare();

        String exchange = "amq.rabbitmq.log";
        channel.queueBind(declareOk1.getQueue(), exchange, "error");
        channel.queueBind(declareOk2.getQueue(), exchange, "waring");
        channel.queueBind(declareOk3.getQueue(), exchange, "info");

        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        channel.basicConsume(declareOk1.getQueue(), false, queueingConsumer);
        channel.basicConsume(declareOk2.getQueue(), false, queueingConsumer);
        channel.basicConsume(declareOk3.getQueue(), false, queueingConsumer);

        while (true){
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            System.out.println("log: "+new String(delivery.getBody()));
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }

    public static void t1()throws Exception{
        Channel channel = generateChannel();
        //channel.exchangeDeclare(TestAmqp.ExchangerName, "direct", true, false, null);
        //channel.exchangeDeclare(TestAmqp.FanoutExchangerName, "fanout", true, false, null);
        channel.exchangeDeclare(TestAmqp.TopicExchangerName, "topic", false, false, null);
        channel.queueDeclare(TestAmqp.TopicQueueName, false, false, false, null);
        //channel.queueBind(TestAmqp.TopicQueueName, TestAmqp.TopicExchangerName, TestAmqp.RouteingName);
        //channel.queueBind(TestAmqp.TopicQueueName, TestAmqp.TopicExchangerName, "*");
        channel.queueBind(TestAmqp.TopicQueueName, TestAmqp.TopicExchangerName, "#.*");

        //channel.basicPublish(TestAmqp.ExchangerName, TestAmqp.RouteingName, null, "haha".getBytes());

        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        channel.basicQos(1);
        channel.basicConsume(TestAmqp.TopicQueueName, queueingConsumer);

        while (true){
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            System.out.println(new String(delivery.getBody()));

            long deliveryTag = delivery.getEnvelope().getDeliveryTag();
            System.out.println("start basicAck:"+deliveryTag);
            //if (true)throw new RuntimeException("xxxxx");
            channel.basicAck(deliveryTag, false);
        }

        //channel.close();
        //connection.close();
    }
}
