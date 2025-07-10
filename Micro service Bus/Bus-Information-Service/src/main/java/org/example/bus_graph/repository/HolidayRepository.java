package org.example.bus_graph.repository;

import org.example.bus_graph.node.Holiday;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayRepository extends Neo4jRepository<Holiday, Long> { }



