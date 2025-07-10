package org.example.bus_graph.Dto.SoapDto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@XmlRootElement(name="stop")
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates an all-argument constructor
public class StopSoapDto {

    private TimeDto departureTime;

    private TimeDto arrivalTime;
    private String stationName;


    @Override
    public String toString() {
        return "StopSoapDto{" +
                "departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", stationName='" + stationName + '\'' +
                '}';
    }







}