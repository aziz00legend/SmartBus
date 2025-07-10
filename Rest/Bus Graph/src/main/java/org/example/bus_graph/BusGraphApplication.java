package org.example.bus_graph;

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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootApplication
public class BusGraphApplication {

	public void run() {

	}

	@Bean
	Configuration cypherDslConfiguration() {
		return Configuration.newConfig()
				.withDialect(Dialect.NEO4J_5).build();
	}

	@Bean
	CommandLineRunner start(BusService busService, StationRepository stationRepository, RouteRepository routeRepository, StopService stopService, CircuitRepository circuitRepository, BusRepository busRepository, CircuitService circuitService) {
		return args -> {


			LocalTime timetowent = LocalTime.of(23,0);
			LocalTime b = LocalTime.of(1,0);
			LocalTime c = LocalTime.of(23,0);
			LocalDate currentDate = LocalDate.now();



			System.out.println(circuitService.totalCostbyDay(currentDate));
			//System.out.println(circuitService.closetStation(80.0522f,40.0522f,"Station C",timetowent,b,c));
/*
			List<Circuit> circuits = circuitRepository.findCircuitsContainingSequence("Station A","Station B");

			circuits.stream().map(Circuit::getCode).forEach(System.out::println);
			System.out.println(busRepository.findBusByCircuitId(circuits.get(0).getId()).get(0).getCapacite());


			*/


			//System.out.println(circuitService.getAllCircuitsBetweenStation("Station A","Station C"));








/*


			//add bus
			Bus bus = new Bus();
			bus.setConsommationParM(0.005f);
			bus.setCapacite(50);
			bus.setType(BusType.NORMAL);
			bus.setPrixParM(0.00015f);

			Long a= busService.createBus(bus).getId();


			//add circuit
			Circuit circuit = new Circuit();
			circuit.setCode("ST322");
			circuit.setWeekend(TRUE);
			circuit.setMondayToFriday(TRUE);
			busService.addCircuitToBus(a,circuit);

			Circuit circuit2 = new Circuit();
			circuit2.setCode("NT650");
			circuit2.setWeekend(TRUE);
			circuit2.setMondayToFriday(TRUE);
			busService.addCircuitToBus(a,circuit2);


			Circuit circuit3 = new Circuit();
			circuit3.setCode("NT322RT");
			circuit3.setWeekend(FALSE);
			circuit3.setMondayToFriday(TRUE);
			busService.addCircuitToBus(a,circuit3);








			// add station
			Station station1 = new Station();
			station1.setCoordinateX(34.0522f);
			station1.setCoordinateY(-118.2437f);
			station1.setName("Station A");
			stationRepository.save(station1);

			Station station2 = new Station();
			station2.setCoordinateX(34.0736f);
			station2.setCoordinateY(-118.2400f);
			station2.setName("Station B");
			stationRepository.save(station2);

			Station station3 = new Station();
			station3.setCoordinateX(34.7522f);
			station3.setCoordinateY(-118.9437f);
			station3.setName("Station C");
			Station station4 =stationRepository.save(station3);




			// Create and save a route (undirected relationship) between the two stations
			Route routeAB = new Route();
			routeAB.setDistance(3750);
			routeAB.setTravelTime(15); // Example travel time in minutes
			routeAB.setRushTime(5); // Example empty time in minutes
			station1.getConnectedRoute().add(routeAB);
			routeAB.setTargetStation(station2);

			Route routeBA = new Route();
			routeBA.setDistance(4000);
			routeBA.setTravelTime(17); // Example travel time in minutes
			routeBA.setRushTime(6); // Example empty time in minutes
			station2.getConnectedRoute().add(routeBA);
			routeBA.setTargetStation(station1);







			Route routeBC = new Route();
			routeBC.setDistance(3800);
			routeBC.setTravelTime(20);
			routeBC.setRushTime(7);
			station2.getConnectedRoute().add(routeBC);
			routeBC.setTargetStation(station3);


			Route routeCB = new Route();
			routeCB.setDistance(3200);
			routeCB.setTravelTime(18);
			routeCB.setRushTime(3);
			station3.getConnectedRoute().add(routeCB);
			routeCB.setTargetStation(station2);




			Route routeCA = new Route();
			routeCA.setDistance(6000); // Example distance in miles
			routeCA.setTravelTime(30); // Example travel time in minutes
			routeCA.setRushTime(10); // Example empty time in minutes
			station3.getConnectedRoute().add(routeCA);
			routeCA.setTargetStation(station1);




			Route routeAC = new Route();
			routeAC.setDistance(6500); // Example distance in miles
			routeAC.setTravelTime(35); // Example travel time in minutes
			routeAC.setRushTime(15); // Example empty time in minutes
			station1.getConnectedRoute().add(routeAC);
			routeAC.setTargetStation(station3);








			stationRepository.save(station1);




















			Stop stop1 =new Stop();
			stop1.setStopCode("S11");
			stop1.setDepartureTime(LocalTime.of(8, 30));
			Stop stop2 =new Stop();
			stop2.setStopCode("S12");
			stop2.setDepartureTime(LocalTime.of(9, 30));
			Stop stop3 =new Stop();
			stop3.setStopCode("S13");
			stop3.setDepartureTime(LocalTime.of(10, 30));

			stop1.setNextStop(stop2);
			stop2.setNextStop(stop3);

			stop1.setStation(station1);
			stop2.setStation(station2);
			stop3.setStation(station3);

			circuit.getStops().add(stop1);
			circuit.getStops().add(stop2);
			circuit.getStops().add(stop3);
			circuit.setStartingStop(stop1);
			circuitRepository.save(circuit);



			Stop stop4 =new Stop();
			stop4.setStopCode("S21");
			stop4.setDepartureTime(LocalTime.of(11, 30));
			Stop stop5 =new Stop();
			stop5.setStopCode("S22");
			stop5.setDepartureTime(LocalTime.of(13, 30));
			stop4.setNextStop(stop5);
			stop4.setStation(station1);
			stop5.setStation(station3);
			circuit2.getStops().add(stop4);
			circuit2.getStops().add(stop5);
			circuit2.setStartingStop(stop4);
			circuitRepository.save(circuit2);







			Stop stop6 =new Stop();
			stop6.setStopCode("S31");
			stop6.setDepartureTime(LocalTime.of(14, 30));
			Stop stop7 =new Stop();
			stop7.setStopCode("S32");
			stop7.setDepartureTime(LocalTime.of(15, 30));
			Stop stop8 =new Stop();
			stop8.setStopCode("S33");
			stop8.setDepartureTime(LocalTime.of(16, 30));

			stop6.setNextStop(stop7);
			stop7.setNextStop(stop8);

			stop6.setStation(station3);
			stop7.setStation(station2);
			stop8.setStation(station1);

			circuit3.getStops().add(stop6);
			circuit3.getStops().add(stop7);
			circuit3.getStops().add(stop8);
			circuit3.setStartingStop(stop6);
			circuitRepository.save(circuit3);

















			// Output stations and the route
			System.out.println("Stations connected: " + station1.getName() + " <--> " + station2.getName());
*/

		};
	}


	public static void main(String[] args) {



		SpringApplication.run(BusGraphApplication.class, args);




	}
}

