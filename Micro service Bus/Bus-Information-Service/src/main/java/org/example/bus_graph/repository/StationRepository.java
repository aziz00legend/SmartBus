package org.example.bus_graph.repository;

import org.example.bus_graph.node.Bus;
import org.example.bus_graph.node.Station;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StationRepository extends Neo4jRepository<Station, Long> {

    @Query("MATCH (s:Station) WHERE s.name = $name RETURN s")
    Optional<Station> findByName(String name);

}