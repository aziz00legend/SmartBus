package org.example.bus_graph.node;

import lombok.*;
import org.example.bus_graph.relationship.Route;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Node
@Setter
@Getter
@ToString
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates an all-argument constructor
public class Stop {
    @Id
    @GeneratedValue
    private Long id;
    private LocalTime departureTime;
    private String stopCode;

    @Relationship(type = "STOP_BY")
    private Station station;

    @Relationship(type = "NEXT", direction = Relationship.Direction.OUTGOING)
    private Stop nextStop;

    @Relationship(type = "PART_OF", direction = Relationship.Direction.INCOMING)
    private Circuit circuit;
}