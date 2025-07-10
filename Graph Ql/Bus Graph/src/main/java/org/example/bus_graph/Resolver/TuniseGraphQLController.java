package org.example.bus_graph.Resolver;

import org.example.bus_graph.Dto.CircuitDistanceDto;
import org.example.bus_graph.Dto.CircuitDto;
import org.example.bus_graph.service.CircuitService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class TuniseGraphQLController {

    private final CircuitService circuitService;

    public TuniseGraphQLController(CircuitService circuitService) {
        this.circuitService = circuitService;
    }

    @QueryMapping
    public List<CircuitDto> getAllCircuitsBetweenStation(
            @Argument String fromStation,
            @Argument String toStation) {
        return circuitService.getAllCircuitsBetweenStation(fromStation, toStation);
    }

    @QueryMapping
    public List<CircuitDistanceDto> getClosestStations(
            @Argument float coordinateX,
            @Argument float coordinateY,
            @Argument String destinationStation,
            @Argument String arrivalTimeLimit,
            @Argument String earlyDepartureTime,
            @Argument String departureTimeLimit) {

        LocalTime arrival = arrivalTimeLimit != null ? LocalTime.parse(arrivalTimeLimit) : null;
        LocalTime earlyDeparture = earlyDepartureTime != null ? LocalTime.parse(earlyDepartureTime) : null;
        LocalTime departureLimit = departureTimeLimit != null ? LocalTime.parse(departureTimeLimit) : null;

        return circuitService.closetStation(coordinateX, coordinateY, destinationStation, arrival, earlyDeparture, departureLimit);
    }

    @QueryMapping
    public float getTotalCostByDay(@Argument String date) {
        LocalDate localDate = LocalDate.parse(date);
        return circuitService.totalCostbyDay(localDate);
    }

    @QueryMapping
    public boolean isCircuitActiveOnDate(
            @Argument Long circuitID,
            @Argument String date) {
        LocalDate localDate = LocalDate.parse(date);
        return circuitService.isCircuitActive(circuitID, localDate);
    }
}
