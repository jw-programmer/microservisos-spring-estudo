package br.com.jwprogammer.avalicacao;

import jdk.jfr.Enabled;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableRabbit
public class AvalicaoDeCreditoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvalicaoDeCreditoApplication.class, args);
	}

}
