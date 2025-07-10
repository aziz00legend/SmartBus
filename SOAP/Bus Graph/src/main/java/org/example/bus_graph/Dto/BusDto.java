package org.example.bus_graph.Dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;
import org.example.bus_graph.Enum.BusType;


@XmlRootElement(name="bus")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusDto {

    private int capacite;
    private BusType type;

    @Override
    public String toString() {
        return "BusDto{" +
                "capacite=" + capacite +
                ", type=" + type +
                '}';
    }
}
