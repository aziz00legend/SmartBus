package org.example.bus_graph.repository;

import org.example.bus_graph.node.Bus;
import org.example.bus_graph.relationship.Route;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends Neo4jRepository<Route, Long> {


    @Query("""
    MATCH (startStation:Station)-[r:ROUTE]->(endStation:Station)
    WHERE id(startStation) = $startStationId AND id(endStation) = $endStationId
    RETURN  properties(r)
    ORDER BY r.distance ASC
    LIMIT 1""")
    Route findShortestRouteBetweenStationsDirctedLinkedOnlyProprite(Long startStationId, Long endStationId);





}