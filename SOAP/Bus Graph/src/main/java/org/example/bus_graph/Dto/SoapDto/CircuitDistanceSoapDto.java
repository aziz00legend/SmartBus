package org.example.bus_graph.Dto.SoapDto;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@XmlRootElement(name="circuitDistance")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CircuitDistanceSoapDto {



    private CircuitSoapDto circuitSoapDto;

    private float distanceStation;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CircuitDistanceSoapDto :\n");
        sb.append("circuitSoapDto : ").append(circuitSoapDto).append("\n");
        sb.append("distanceStation : ").append(distanceStation).append("\n");
        return sb.toString();
    }
}
