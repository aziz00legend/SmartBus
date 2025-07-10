package org.example.bus_graph.Dto.SoapDto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@XmlRootElement(name="TimeDto")
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor
public class TimeDto {

    private int hour;
    private int minute;
    @Override
    public String toString() {
        return "TimeDto{" +
                "hour=" + hour +
                ", minute=" + minute +
                '}';
    }
}
