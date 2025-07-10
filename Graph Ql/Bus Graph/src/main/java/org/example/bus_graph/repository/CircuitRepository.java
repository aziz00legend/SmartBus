package org.example.bus_graph.repository;

import org.example.bus_graph.node.Bus;
import org.example.bus_graph.node.Circuit;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CircuitRepository extends Neo4jRepository<Circuit, Long> {


    @Query("""
       MATCH (c:Circuit)-[:PART_OF]->(startStop:Stop)-[:STOP_BY]->(startStation:Station),
             (c)-[:PART_OF]->(endStop:Stop)-[:STOP_BY]->(endStation:Station)
       WHERE startStation.name = $startStationName 
         AND endStation.name = $endStationName
         AND EXISTS {
             MATCH path = (startStop)-[:NEXT*]->(endStop)
             RETURN path
         }
       RETURN c
       """)
    List<Circuit> findCircuitsContainingSequence(String startStationName, String endStationName);



    @Query("""
       MATCH (c:Circuit)-[:PART_OF]->(startStop:Stop)-[:STOP_BY]->(startStation:Station),
             (c)-[:PART_OF]->(stop:Stop)-[:STOP_BY]->(station:Station)
       WHERE station.name = $endStationName
         AND startStation.name <> $endStationName
         AND EXISTS {
             MATCH path = (startStop)-[:NEXT*]->(stop)
             RETURN path
         }
       RETURN DISTINCT c
       """)
    List<Circuit> findCircuitsPassingOrEndingAtStation(String endStationName);






}