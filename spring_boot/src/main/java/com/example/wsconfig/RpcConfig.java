package com.example.wsconfig;

import com.example.service.PersonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.BurlapServiceExporter;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 * Created by zhougb on 2016/8/19.
 */
@Configuration
public class RpcConfig {
    @Bean
    public RmiServiceExporter rmiServiceExporter(PersonService personService){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("personService");
        rmiServiceExporter.setService(personService);
        rmiServiceExporter.setServiceInterface(PersonService.class);
        return rmiServiceExporter;
    }

    //@Bean
    public RmiProxyFactoryBean rmiProxyFactoryBean(){
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost/personService");
        rmiProxyFactoryBean.setServiceInterface(PersonService.class);
        return rmiProxyFactoryBean;
    }

    //for hessian
    @Bean(name= "/hessianExportedPersonService")
    public HessianServiceExporter hessianServiceExporter(PersonService personService){
        HessianServiceExporter hessianServiceExporter = new HessianServiceExporter();
        hessianServiceExporter.setServiceInterface(PersonService.class);
        hessianServiceExporter.setService(personService);
        return hessianServiceExporter;
    }

    @Bean(name= "/burlapExportedPersonService")
    public BurlapServiceExporter burlapServiceExporter(PersonService personService){
        BurlapServiceExporter burlapServiceExporter = new BurlapServiceExporter();
        burlapServiceExporter.setServiceInterface(PersonService.class);
        burlapServiceExporter.setService(personService);
        return burlapServiceExporter;
    }

    //@Bean
    public SimpleJaxWsServiceExporter simpleJaxWsServiceExporter(){
        SimpleJaxWsServiceExporter simpleJaxWsServiceExporter =  new SimpleJaxWsServiceExporter();
        simpleJaxWsServiceExporter.setBaseAddress("http://localhost:8080/jaxws/");
        return simpleJaxWsServiceExporter;
    }
}