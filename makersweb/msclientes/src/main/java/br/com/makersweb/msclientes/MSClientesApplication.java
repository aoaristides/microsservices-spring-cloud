package br.com.makersweb.msclientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MSClientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MSClientesApplication.class, args);
	}

}
