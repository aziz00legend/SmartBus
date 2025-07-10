package org.example.bus_graph.Dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CircuitDistanceDto {



    private CircuitDto circuitDto;

    private float distanceStation;
}
