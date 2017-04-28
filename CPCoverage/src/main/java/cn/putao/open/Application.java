package cn.putao.open;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by putao_lhq on 17-4-24.
 */
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
@ImportResource("classpath:spring-mybatis.xml")
public class Application
{
    public static void main(String[] args)
    {
        SpringApplication application = new SpringApplication(Application.class);
        application.run(args);
    }
}
