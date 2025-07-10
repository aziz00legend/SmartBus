package org.example.bus_graph.service;

import org.example.bus_graph.Dto.CircuitDistanceDto;
import org.example.bus_graph.Dto.CircuitDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/tunise")
public class TuniseWebService {

    private final CircuitService circuitService;

    @Autowired
    public TuniseWebService(CircuitService circuitService) {
        this.circuitService = circuitService;
    }

    /**
     * Endpoint to get all circuits between two stations.
     */
    @GetMapping("/circuits")
    public List<CircuitDto> getAllCircuitsBetweenStation(
            @RequestParam(name = "fromStation") String fromStation,
            @RequestParam(name = "toStation") String toStation) {
        return circuitService.getAllCircuitsBetweenStation(fromStation, toStation);
    }

    /**
     * Endpoint to get the closest stations based on coordinates and other criteria.
     */
    @GetMapping("/closest-stations")
    public List<CircuitDistanceDto> getClosestStations(
            @RequestParam(name = "coordinateX") float coordinateX,
            @RequestParam(name = "coordinateY") float coordinateY,
            @RequestParam(name = "destinationStation") String destinationStation,
            @RequestParam(name = "arrivalTimeLimit") LocalTime arrivalTimeLimit,
            @RequestParam(name = "earlyDepartureTime") LocalTime earlyDepartureTime,
            @RequestParam(name = "departureTimeLimit") LocalTime departureTimeLimit) {
        return circuitService.closetStation(coordinateX, coordinateY, destinationStation, arrivalTimeLimit, earlyDepartureTime, departureTimeLimit);
    }

    /**
     * Endpoint to check if a circuit is active on a specific date.
     */
    @GetMapping("/circuits/{circuitID}/active")
    public boolean isCircuitActiveOnDate(
            @PathVariable(name = "circuitID") Long circuitID,
            @RequestParam(name = "date") LocalDate date) {
        return circuitService.isCircuitActive(circuitID, date);
    }
}
