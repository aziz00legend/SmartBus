package org.example.clientsoapbus;

import client.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.List;


import java.time.format.DateTimeFormatter;



@SpringBootApplication
public class ClientSoapBusApplication {







    public static void main(String[] args) {
        SpringApplication.run(ClientSoapBusApplication.class, args);
        TuniseWebService_Service service = new TuniseWebService_Service();
        TuniseWebService port = service.getTuniseWebServicePort();

        System.out.println("Invocation de méthode 'getAllCircuitsBetweenStation' :");
        List<CircuitSoapDto> circuits = port.getAllCircuitsBetweenStation("Station A", "Station C");
        System.out.println((port.afficherCircuitSoapDto(circuits.get(0))));







        // 2. getClosestStations
        System.out.println("\nInvocation de méthode 'getClosestStations' :");
        TimeDto arrivalTimeLimit = new TimeDto();
        arrivalTimeLimit.setHour(10);
        arrivalTimeLimit.setMinute(30);
        TimeDto earlyDepartureTime = new TimeDto();
        earlyDepartureTime.setHour(8);
        earlyDepartureTime.setMinute(0);
        TimeDto departureTimeLimit = new TimeDto(); // 12:00 PM
        departureTimeLimit.setHour(12);
        departureTimeLimit.setMinute(0);
        List<CircuitDistanceSoapDto> closestStations = port.getClosestStations(
                34.0736f, -118.24f, "Station C", arrivalTimeLimit, earlyDepartureTime, departureTimeLimit);
        closestStations.forEach(circuitDistanceSoapDto ->
        {
            System.out.println(port.afficherCircuitDistanceSoapDto(circuitDistanceSoapDto));
        });

        // 3. getTotalCostByDay
        System.out.println("\nInvocation de méthode 'getTotalCostByDay' :");
        DateDto date = new DateDto();
        date.setDay(26);
        date.setMonth(12);
        date.setYear(2024);
        float totalCost = port.getTotalCostByDay(date);
        System.out.println("Total cost for " + port.afficherDateDto(date) + ": " + totalCost);

        // 4. isCircuitActiveOnDate
        System.out.println("\nInvocation de méthode 'isCircuitActiveOnDate' :");
        boolean isActive = port.isCircuitActiveOnDate(25L, date); // Assuming circuit ID is 1
        System.out.println("Is circuit active on " + port.afficherDateDto(date) + "? " + isActive);
    }

    }

