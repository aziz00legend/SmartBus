package org.example.bus_graph.node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;



import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Node
@Setter
@Getter
@ToString
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates an all-argument constructor
public class Circuit {
    @Id @GeneratedValue
    private Long id;

    private boolean weekend;
    private boolean holidayWork;
    private boolean mondayToFriday;
    private String code;



    @JsonIgnore
    @Relationship(type = "BEGIN")
    private Stop startingStop;
    @JsonIgnore
    @Relationship(type = "PART_OF")
    private List<Stop> stops = new ArrayList<Stop>();





    // Getters and Setters
}