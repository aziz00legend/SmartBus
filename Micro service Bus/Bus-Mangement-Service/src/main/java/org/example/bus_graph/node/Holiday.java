package org.example.bus_graph.node;

import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Node
@Setter
@Getter
@ToString
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates an all-argument constructor
public class Holiday {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate startDate;
    private LocalDate  endDate;
    private String name;


    @Relationship(type = "WORK_ON")
    private List<Circuit> circuits;


}
