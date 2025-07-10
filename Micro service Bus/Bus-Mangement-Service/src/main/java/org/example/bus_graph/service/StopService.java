package org.example.bus_graph.service;

import org.example.bus_graph.node.Stop;
import org.example.bus_graph.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StopService {

    @Autowired
    private StopRepository stopRepository;

    // Create a Stop
    public Stop createStop(Stop stop) {
        return stopRepository.save(stop);
    }

    // Retrieve a Stop by ID
    public Optional<Stop> getStopById(Long id) {
        return stopRepository.findById(id);
    }

    // Retrieve all Stops
    public List<Stop> getAllStops() {
        return stopRepository.findAll();
    }

    // Update an existing Stop
    public Stop updateStop(Long id, Stop updatedStop) {
        return stopRepository.findById(id)
                .map(stop -> {
                    stop.setDepartureTime(updatedStop.getDepartureTime());
                    stop.setStation(updatedStop.getStation());
                    return stopRepository.save(stop);
                })
                .orElseThrow(() -> new RuntimeException("Stop not found with ID: " + id));
    }

    // Delete a Stop
    public void deleteStop(Long id) {
        stopRepository.deleteById(id);
    }
}
