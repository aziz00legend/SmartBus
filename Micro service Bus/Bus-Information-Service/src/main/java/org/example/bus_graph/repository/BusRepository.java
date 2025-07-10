package org.example.bus_graph.repository;

import org.example.bus_graph.node.Bus;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends Neo4jRepository<Bus, Long> {


    @Query("MATCH (b:Bus) WHERE b.capacite > $capacity RETURN b")
    List<Bus> findBusesWithCapacityGreaterThan(int capacity);

    @Query("""
       MATCH (b:Bus)-[:OPERATES_ON]->(c:Circuit)
       WHERE id(c) = $circuitId
       RETURN b
       """)
    List<Bus> findBusByCircuitId(Long circuitId);



}