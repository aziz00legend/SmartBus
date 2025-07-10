package org.example.bus_graph.repository;

import org.example.bus_graph.node.Stop;
import org.example.bus_graph.relationship.Route;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

public interface StopRepository extends Neo4jRepository<Stop, Long> { }
