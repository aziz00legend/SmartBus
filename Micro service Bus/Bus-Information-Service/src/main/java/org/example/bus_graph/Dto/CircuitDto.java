package org.example.bus_graph.Dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@XmlRootElement

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CircuitDto {
    private String code;
    private boolean weekend;
    private boolean holidayWork;
    private boolean mondayToFriday;
    private BusDto busDto;
    private List<StopDto> stopDtos = new ArrayList<>();
    private float distance;
    private float duration;
    private float price;


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CircuitDto :\n");
        sb.append("code : ").append(code).append("\n");
        sb.append("weekend : ").append(weekend).append("\n");
        sb.append("holidayWork : ").append(holidayWork).append("\n");
        sb.append("mondayToFriday : ").append(mondayToFriday).append("\n");
        sb.append("busDto : ").append(busDto).append("\n");
        sb.append("stopSoap :\n");
        for (int i = 0; i < stopDtos.size(); i++) {
            sb.append("stop ").append(i + 1).append(" : ").append(stopDtos.get(i)).append("\n");
        }
        sb.append("distance : ").append(distance).append("\n");
        sb.append("duration : ").append(duration).append("\n");
        sb.append("price : ").append(price).append("\n");
        return sb.toString();
    }


}
