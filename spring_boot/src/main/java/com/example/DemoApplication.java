package com.example;

import com.example.beans.Person;
import com.example.dao.PersonRepository;
import com.example.dao.specs.CustomRepositoryFactoryBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SpringBootApplication
//@Controller
@Controller
//@EnableJpaRepositories(repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class)
//@EnableCaching
//@ComponentScan("com.example.*")
//public class DemoApplication extends SpringBootServletInitializer {
//public class DemoApplication extends AbstractAnnotationConfigDispatcherServletInitializer{
public class DemoApplication {
	private static final Log logger = LogFactory.getLog(DemoApplication.class);

	/*@Autowired
	private PersonRepository personRepository;*/

	//@Autowired
	private User user;


	@Autowired
	public void setUser(User user) {

		this.user = user;
	}


/*@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[0];
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[0];
	}

	@Override
	protected String[] getServletMappings() {
		return new String[0];
	}*/


	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return super.configure(builder);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		logger.info("onStartup");
		servletContext.addListener( new RequestContextListener());
	}*/

	/*@Bean
	public RequestContextListener requestContextListener(){
		return new RequestContextListener();
	}*/

	@RequestMapping("/index")
	public String index(){
		logger.info("user.getAge: 4");

		logger.info("user.getAge: user:"+user.getAge());
		//return "Hello Spring Boot.";
		return "ws";
	}

	@RequestMapping("/login")
	public String login(){
		return "login";
	}

	@RequestMapping("/chat")
	public void  chat(){
	}

	@RequestMapping("/testwebsocket.html")
	public void  testwebsocket(){
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	/*@Autowired
	private RabbitTemplate rabbitTemplate;

	public void run(String... args) throws Exception {
		Person person = new Person();
		person.setName("afa");
		person.setAge(23);
		person.setAddress("afdads");
		//rabbitTemplate.convertAndSend("person.queue", "asdfasdfasdfdsaf");
		rabbitTemplate.convertAndSend("person.queue", person);
	}*/
}
