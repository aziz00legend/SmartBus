package org.example.bus_graph.Dto.SoapDto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;
import org.example.bus_graph.Dto.BusDto;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="circuit")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CircuitSoapDto {
    private String code;
    private boolean weekend;
    private boolean holidayWork;
    private boolean mondayToFriday;
    private BusDto busDto;
    private List<StopSoapDto> stopSoapDtos = new ArrayList<>();
    private float distance;
    private float duration;
    private float price;


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CircuitSoapDto :\n");
        sb.append("code : ").append(code).append("\n");
        sb.append("weekend : ").append(weekend).append("\n");
        sb.append("holidayWork : ").append(holidayWork).append("\n");
        sb.append("mondayToFriday : ").append(mondayToFriday).append("\n");
        sb.append("busDto : ").append(busDto).append("\n");
        sb.append("stopSoapDtos :\n");
        for (int i = 0; i < stopSoapDtos.size(); i++) {
            sb.append("stop ").append(i + 1).append(" : ").append(stopSoapDtos.get(i)).append("\n");
        }
        sb.append("distance : ").append(distance).append("\n");
        sb.append("duration : ").append(duration).append("\n");
        sb.append("price : ").append(price).append("\n");
        return sb.toString();
    }

}
