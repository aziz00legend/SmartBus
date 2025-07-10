package org.example.bus_graph.service;

import org.example.bus_graph.relationship.Route;
import org.example.bus_graph.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    // Create a Route
    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }

    // Retrieve a Route by ID
    public Optional<Route> getRouteById(Long id) {
        return routeRepository.findById(id);
    }

    // Retrieve all Routes
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    // Update an existing Route
    public Route updateRoute(Long id, Route updatedRoute) {
        return routeRepository.findById(id)
                .map(route -> {
                    route.setDistance(updatedRoute.getDistance());
                    route.setTravelTime(updatedRoute.getTravelTime());
                    route.setRushTime(updatedRoute.getRushTime());
                    return routeRepository.save(route);
                })
                .orElseThrow(() -> new RuntimeException("Route not found with ID: " + id));
    }

    // Delete a Route
    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
    }


}
