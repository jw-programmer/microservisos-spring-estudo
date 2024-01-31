package br.com.jwprogrammer.eurekacartoes;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class EurekaCartoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaCartoesApplication.class, args);
	}

}
