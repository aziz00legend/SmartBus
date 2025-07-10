package org.example.bus_graph.service;


import org.example.bus_graph.node.Bus;
import org.example.bus_graph.node.Circuit;
import org.example.bus_graph.repository.BusRepository;
import org.example.bus_graph.repository.CircuitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private CircuitRepository circuitRepository;

    // Create a new Bus
    public Bus createBus(Bus bus) {
        return busRepository.save(bus);
    }

    // Retrieve a Bus by its ID
    public Optional<Bus> getBusById(Long id) {
        return busRepository.findById(id);
    }

    // Retrieve all Buses
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    // Update an existing Bus
    public Bus updateBus(Long id, Bus updatedBus) {
        return busRepository.findById(id)
                .map(bus -> {
                    bus.setConsommationParM(updatedBus.getConsommationParM());
                    bus.setCapacite(updatedBus.getCapacite());
                    bus.setType(updatedBus.getType());
                    bus.setPrixParM(updatedBus.getPrixParM());
                    return busRepository.save(bus);
                })
                .orElseThrow(() -> new RuntimeException("Bus not found with ID: " + id));
    }

    // Delete a Bus by its ID
    public void deleteBus(Long id) {
        busRepository.deleteById(id);
    }

    // Add a Circuit to a Bus
    public Bus addCircuitToBus(Long busId, Circuit circuitId) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found with ID: " + busId));
/*
        Circuit circuit = circuitRepository.findById(circuitId)
                .orElseThrow(() -> new RuntimeException("Circuit not found with ID: " + circuitId));
*/
        bus.getCircuits().add(circuitId);
        return busRepository.save(bus);
    }
}
