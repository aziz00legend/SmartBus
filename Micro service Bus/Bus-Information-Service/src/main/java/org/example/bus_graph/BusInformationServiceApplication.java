package org.example.bus_graph;

import org.example.bus_graph.service.*;
import org.neo4j.cypherdsl.core.renderer.Configuration;
import org.neo4j.cypherdsl.core.renderer.Dialect;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BusInformationServiceApplication {

	public void run() {

	}

	@Bean
	Configuration cypherDslConfiguration() {
		return Configuration.newConfig()
				.withDialect(Dialect.NEO4J_5).build();
	}

	@Bean
	CommandLineRunner start( CircuitService circuitService) {
		return args -> {
			System.out.println(circuitService.getAllCircuitsBetweenStation("Station A" ,"Station B" ));

















		};
	}


	public static void main(String[] args) {



		SpringApplication.run(BusInformationServiceApplication.class, args);




	}
}

