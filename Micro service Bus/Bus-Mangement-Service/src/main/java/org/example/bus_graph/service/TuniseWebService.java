package org.example.bus_graph.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;

import org.example.bus_graph.Dto.CircuitDtoSave;
import org.example.bus_graph.node.Circuit;
import org.example.bus_graph.node.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Mangment")
public class TuniseWebService {


    private final CircuitService circuitService;
    private final StationService stationService;

    @Autowired
    public TuniseWebService(CircuitService circuitService, StationService stationService) {
        this.circuitService = circuitService;
        this.stationService = stationService;
    }








    // 1. Create a new Circuit
    @PostMapping("/create")
    public ResponseEntity<Circuit> createCircuit(
            @RequestParam Long busId,
            @RequestBody CircuitDtoSave circuitDto,
            @RequestParam List<Long> stationIds,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
            @RequestParam Integer timeSpentAtEveryStation) {
        Circuit createdCircuit = circuitService.createCircuitByInfo(busId, circuitDto, stationIds, startTime, timeSpentAtEveryStation);
        return ResponseEntity.ok(createdCircuit);
    }

    // 2. Get all Circuits
    @GetMapping
    public ResponseEntity<List<Circuit>> getAllCircuits() {
        List<Circuit> circuits = circuitService.getAllCircuits();
        return ResponseEntity.ok(circuits);
    }

    // 3. Get Circuit by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Circuit>> getCircuitById(@PathVariable Long id) {
        Optional<Circuit> circuit = circuitService.getCircuitById(id);
        return ResponseEntity.ok(circuit);
    }

    // 4. Calculate total cost by day
    @GetMapping("/total-cost")
    public ResponseEntity<Float> getTotalCostByDay(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        float totalCost = circuitService.totalCostbyDay(date);
        return ResponseEntity.ok(totalCost);
    }

    // 5. Check if a circuit is active on a specific day
    @GetMapping("/{circuitId}/is-active")
    public ResponseEntity<Boolean> isCircuitActive(
            @PathVariable Long circuitId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        boolean isActive = circuitService.isCircuitActive(circuitId, date);
        return ResponseEntity.ok(isActive);
    }

    // 6. Add a Route to a Station
    @PostMapping("/stations/{stationS}/add-route")
    public ResponseEntity<Station> addRouteToStation(
            @PathVariable Long stationS,
            @RequestParam Long stationT,
            @RequestParam int distance,
            @RequestParam int travelTime,
            @RequestParam int rushHour) {
        Station updatedStation = stationService.addRouteToStation(stationS, stationT, distance, travelTime, rushHour);
        return ResponseEntity.ok(updatedStation);
    }

    // 7. Create a new Station
    @PostMapping("/stations/create")
    public ResponseEntity<Station> createStation(@RequestBody Station station) {
        Station createdStation = stationService.createStation(station);
        return ResponseEntity.ok(createdStation);
    }

    // 8. Retrieve a Station by ID
    @GetMapping("/stations/{id}")
    public ResponseEntity<Optional<Station>> getStationById(@PathVariable Long id) {
        Optional<Station> station = stationService.getStationById(id);
        return ResponseEntity.ok(station);
    }


}
