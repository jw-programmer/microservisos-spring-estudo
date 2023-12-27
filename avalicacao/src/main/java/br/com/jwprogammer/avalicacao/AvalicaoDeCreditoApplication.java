package br.com.jwprogammer.avalicacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AvalicaoDeCreditoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvalicaoDeCreditoApplication.class, args);
	}

}
