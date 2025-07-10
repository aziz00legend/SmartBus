package org.example.bus_graph.Resolver;

import org.example.bus_graph.Dto.BusDto;
import org.example.bus_graph.Dto.CircuitDto;
import org.example.bus_graph.Dto.StopDto;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CircuitResolver {

    @SchemaMapping
    public BusDto busDto(CircuitDto circuitDto) {
        return circuitDto.getBusDto();
    }

    @SchemaMapping
    public List<StopDto> stopDtos(CircuitDto circuitDto) {
        return circuitDto.getStopDtos();
    }
}
