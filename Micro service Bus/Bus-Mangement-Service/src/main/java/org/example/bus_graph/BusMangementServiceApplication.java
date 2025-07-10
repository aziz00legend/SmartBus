package org.example.bus_graph;

import jakarta.xml.ws.Endpoint;
import org.example.bus_graph.Dto.CircuitDtoSave;
import org.example.bus_graph.Enum.BusType;
import org.example.bus_graph.node.Bus;
import org.example.bus_graph.node.Circuit;
import org.example.bus_graph.node.Station;
import org.example.bus_graph.node.Stop;
import org.example.bus_graph.relationship.Route;
import org.example.bus_graph.repository.BusRepository;
import org.example.bus_graph.repository.CircuitRepository;
import org.example.bus_graph.repository.RouteRepository;
import org.example.bus_graph.repository.StationRepository;
import org.example.bus_graph.service.*;
import org.neo4j.cypherdsl.core.renderer.Configuration;
import org.neo4j.cypherdsl.core.renderer.Dialect;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@SpringBootApplication
public class BusMangementServiceApplication {

	public void run() {

	}

	@Bean
	Configuration cypherDslConfiguration() {
		return Configuration.newConfig()
				.withDialect(Dialect.NEO4J_5).build();
	}

	@Bean
	CommandLineRunner start(  ) {
		return args -> {



		};
	}


	public static void main(String[] args) {



		SpringApplication.run(BusMangementServiceApplication.class, args);




	}
}

