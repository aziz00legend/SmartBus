package org.example.bus_graph.service;

import org.example.bus_graph.node.Station;
import org.example.bus_graph.node.Stop;
import org.example.bus_graph.relationship.Route;
import org.example.bus_graph.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StationService {

    @Autowired
    private StationRepository stationRepository;

    // Create a Station
    public Station createStation(Station station) {
        return stationRepository.save(station);
    }

    // Retrieve a Station by ID
    public Optional<Station> getStationById(Long id) {
        return stationRepository.findById(id);
    }

    // Retrieve all Stations
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    // Update an existing Station
    public Station updateStation(Long id, Station updatedStation) {
        return stationRepository.findById(id)
                .map(station -> {
                    station.setCoordinateX(updatedStation.getCoordinateX());
                    station.setCoordinateY(updatedStation.getCoordinateY());
                    station.setName(updatedStation.getName());
                    return stationRepository.save(station);
                })
                .orElseThrow(() -> new RuntimeException("Station not found with ID: " + id));
    }

    // Add a Route to a Station
    public Station addRouteToStation(Long stationId, Route route) {
        Station station = stationRepository.findById(stationId)
                .orElseThrow(() -> new RuntimeException("Station not found with ID: " + stationId));

        station.getConnectedRoute().add(route);
        return stationRepository.save(station);
    }

    // Delete a Station
    public void deleteStation(Long id) {
        stationRepository.deleteById(id);
    }







}
