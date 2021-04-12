package com.springcloudfunction.startspringcloudfunction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class StartSpringCloudFunctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartSpringCloudFunctionApplication.class, args);
	}

	@Bean
	public Function<String, String> function(){
		return input -> input.toUpperCase();
	}

	@Bean
	public Consumer<String> consumer(){

		return consume -> System.out.println(consume);
	}

	@Bean
	public Supplier<String> supplier(){

		return () -> "hello world BR";
	}



}
