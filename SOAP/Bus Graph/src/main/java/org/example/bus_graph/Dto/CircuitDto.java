package org.example.bus_graph.Dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@XmlRootElement(name="circuit")
@XmlAccessorType(XmlAccessType.FIELD)
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



}
