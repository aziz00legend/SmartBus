package org.example.bus_graph.Dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;
import org.example.bus_graph.node.Circuit;
import org.example.bus_graph.node.Station;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalTime;

@XmlRootElement

@Setter
@Getter
@ToString
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates an all-argument constructor
public class StopDto {

    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private String stationName;






}