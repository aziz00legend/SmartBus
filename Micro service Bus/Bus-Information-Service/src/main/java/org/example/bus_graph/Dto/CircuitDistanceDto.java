package org.example.bus_graph.Dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;
@XmlRootElement

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CircuitDistanceDto {



    private CircuitDto circuitDto;

    private float distanceStation;




    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CircuitDistance :\n");
        sb.append("circuitDto : ").append(circuitDto).append("\n");
        sb.append("distanceStation : ").append(distanceStation).append("\n");
        return sb.toString();
    }
}
