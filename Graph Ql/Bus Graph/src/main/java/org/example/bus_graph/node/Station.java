package org.example.bus_graph.node;

import lombok.*;
import org.example.bus_graph.relationship.Route;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Node
@Setter
@Getter
@ToString
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates an all-argument constructor
public class Station {
    @Id
    @GeneratedValue
    private Long id;

    private float coordinateX;
    private float coordinateY;
    private String name;
    @Relationship(type = "ROUTE")
    private List<Route> connectedRoute = new ArrayList<>();



    // Getters and Setters
}