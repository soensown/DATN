package com.example.datn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class DatnApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatnApplication.class, args);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123456"));
        System.out.println(encoder.encode("abc123"));
    }

}
