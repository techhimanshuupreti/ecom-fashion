package com.devil.ecomfashion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@EnableMethodSecurity
@SpringBootApplication
public class EcommerceWepApp {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(EcommerceWepApp.class, args);

        System.out.println("\n\n" +
                "    ###########################################################################\n" +
                "    \uD83D\uDEE1️  Server Started...\n" +
                "    \uD83D\uDEE1️  \n" +
                "    \uD83D\uDEE1️  Listening on port: " + context.getEnvironment().getProperty("server.port") + "\n" +
                "    \uD83D\uDEE1️  Connected to Database: " + context.getEnvironment().getProperty("spring.datasource.url") + "\n" +
                "    \uD83D\uDEE1️  Swagger URL: http://localhost:" + context.getEnvironment().getProperty("server.port") + "/swagger-ui/index.html." + "\n" +
                "    ###########################################################################\n" +
                "\n\n");

    }
}
