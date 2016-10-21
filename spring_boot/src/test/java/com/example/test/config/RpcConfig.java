package com.example.test.config;

import com.example.service.PersonService;
import com.example.service.TestJaxWsWebService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zhougb on 2016/8/19.
 */
@Configuration
public class RpcConfig {

    @Bean(name = "rmiPersonService")
    public RmiProxyFactoryBean rmiProxyFactoryBean(){
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost/personService");
        rmiProxyFactoryBean.setServiceInterface(PersonService.class);
        return rmiProxyFactoryBean;
    }

    @Bean(name = "hessianPersonService")
    public HessianProxyFactoryBean hessianProxyFactoryBean(){
        HessianProxyFactoryBean hessianProxyFactoryBean = new HessianProxyFactoryBean();
        hessianProxyFactoryBean.setServiceUrl("http://localhost:8080/person.service");
        hessianProxyFactoryBean.setServiceInterface(PersonService.class);
        return hessianProxyFactoryBean;
    }


    //@Bean(name = "jaxwsCurrentDateService")
    public JaxWsPortProxyFactoryBean jaxWsPortProxyFactoryBean(){
        JaxWsPortProxyFactoryBean jaxWsPortProxyFactoryBean = new JaxWsPortProxyFactoryBean();
        try {
            jaxWsPortProxyFactoryBean.setWsdlDocumentUrl(new URL("http://127.0.0.1:8080/jaxws/currentDateService?wsdl"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        jaxWsPortProxyFactoryBean.setPortName("currentDatePort");
        jaxWsPortProxyFactoryBean.setServiceName("currentDateService");
        jaxWsPortProxyFactoryBean.setNamespaceUri("http://test.com");
        jaxWsPortProxyFactoryBean.setServiceInterface(TestJaxWsWebService.class);

        return jaxWsPortProxyFactoryBean;
    }

}
