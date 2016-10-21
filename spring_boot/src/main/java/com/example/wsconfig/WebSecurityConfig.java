package com.example.wsconfig;

import com.example.service.impl.SecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by zhougb on 2016/8/16.
 */
@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    /*@Autowired
    private DataSource dataSource;*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/login").permitAll()
                .antMatchers("/person/*").permitAll()
                .antMatchers("/pc/*").permitAll()
                .antMatchers("/amqp/*").permitAll()
                //.antMatchers("/*.service").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/chat")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.inMemoryAuthentication()
                .withUser("wyf").password("wyf").roles("USER")
                .and()
                .withUser("zgb").password("zgb").roles("USER");*/
        /*auth.jdbcAuthentication().dataSource(dataSource)
        .usersByUsernameQuery("select u.name as username , u.name as password , true from user u where  u.name = ?")
        .authoritiesByUsernameQuery("select u.name as username , 'USER' as authority from user u where  u.name = ?");*/

        auth.userDetailsService(securityUserService());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/static/**");
                //.antMatchers("/*.service")
                //.antMatchers("/jaxws/*");
    }

    @Bean
    SecurityUserService securityUserService(){
        return new SecurityUserService();
    }
}
