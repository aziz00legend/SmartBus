package org.example.bus_graph.relationship;

import lombok.*;
import org.example.bus_graph.node.Station;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;




@RelationshipProperties
@Setter
@Getter
@ToString
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates an all-argument constructor
public class Route {
    @Id
    @GeneratedValue
    private Long id;

    @TargetNode
    private Station TargetStation;




    private int distance;
    private int travelTime;
    private int rushTime;

    // Getters and Setters
}