package br.com.makersweb.msgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class MSGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MSGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder
				.routes()
					.route(r -> r.path("/clientes/**").uri("lb://ms-clientes"))
					.route(r -> r.path("/cartoes/**").uri("lb://ms-cartoes"))
					.route(r -> r.path("/avaliacoes-credito/**").uri("lb://ms-avaliador-credito"))
				.build();
	}

}
