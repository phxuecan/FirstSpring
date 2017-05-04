package com.leehq.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * Created by putao_lhq on 17-5-3.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/", "home").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin();/*
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();*/
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //设置哪些用户可以访问
        /*auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
        auth.inMemoryAuthentication()
                .withUser("lhq").password("lhq123456").roles("USER", "ADMIN");*/
        //通过jdbc方式进行认证授权
        /*auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(getUserQuery())
                .authoritiesByUsernameQuery(getAuthoritiesQuery())
                .rolePrefix("ROLE_");*/
        //自定义UserDetailsService方式
        //auth.userDetailsService(new UserServiceDetail(dataSource));
        //LDAP
        auth.ldapAuthentication()
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups")
                .contextSource(contextSource())
                .passwordCompare()
                .passwordEncoder(new LdapShaPasswordEncoder())
                .passwordAttribute("userPassword");
    }

    private DefaultSpringSecurityContextSource contextSource()
    {
        return new DefaultSpringSecurityContextSource(Arrays.asList("ldap://192.168.1.32:8389/"), "dc=springframework,dc=org");
    }

    private String getUserQuery() {
        return "SELECT username, password, true "
                + "FROM users "
                + "WHERE username = ?";
    }

    private String getAuthoritiesQuery() {
        return "select username, 'USER' from users where username = ? ";
    }

    @Bean
    public DataSource getDataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://192.168.1.32:3306/spring_security");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }
}
