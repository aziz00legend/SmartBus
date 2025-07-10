package org.example.bus_graph.repository;

import org.example.bus_graph.node.Bus;
import org.example.bus_graph.node.Station;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StationRepository extends Neo4jRepository<Station, Long> {



}