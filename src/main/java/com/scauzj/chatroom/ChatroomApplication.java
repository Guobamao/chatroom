package com.scauzj.chatroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class ChatroomApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatroomApplication.class, args);
    }

}
