package com.kaishengit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SpringbootDubboConsumerApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SpringbootDubboConsumerApplication.class, args);
	}
}
