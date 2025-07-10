package org.example.bus_graph.Dto;

import lombok.*;
import org.example.bus_graph.node.Stop;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@ToString
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates an all-argument constructor
public class CircuitDtoSave {


    private boolean weekend;
    private boolean holidayWork;
    private boolean mondayToFriday;
    private String code;








}