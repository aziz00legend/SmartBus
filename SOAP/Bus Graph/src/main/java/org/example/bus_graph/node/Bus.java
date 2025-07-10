package org.example.bus_graph.node;

import lombok.*;

import org.example.bus_graph.Enum.BusType;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Node
@Setter
@Getter
@ToString
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates an all-argument constructor
public class Bus {

    @Id
    @GeneratedValue
    private Long id;

    private float consommationParM;
    private int capacite;
    private BusType type;
    private float prixParM;

    @Relationship(type = "OPERATES_ON")
    private Set<Circuit> circuits;
}