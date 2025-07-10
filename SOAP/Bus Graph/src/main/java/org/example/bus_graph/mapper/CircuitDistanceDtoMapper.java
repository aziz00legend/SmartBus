package org.example.bus_graph.mapper;

import org.example.bus_graph.Dto.CircuitDistanceDto;
import org.example.bus_graph.Dto.SoapDto.CircuitDistanceSoapDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service





public class CircuitDistanceDtoMapper {


    private final CircuitDtoMapper circuitDtoMapper;
    @Autowired
    public CircuitDistanceDtoMapper(CircuitDtoMapper circuitDtoMapper) {
        this.circuitDtoMapper = circuitDtoMapper;
    }




    public CircuitDistanceSoapDto dtotoSoap(CircuitDistanceDto circuitDistanceDto) {
        CircuitDistanceSoapDto result = new CircuitDistanceSoapDto();
        result.setCircuitSoapDto(circuitDtoMapper.dtoToSoap(circuitDistanceDto.getCircuitDto()));
        result.setDistanceStation(circuitDistanceDto.getDistanceStation());
        return result;
    }
    public CircuitDistanceDto soaptoDto(CircuitDistanceSoapDto circuitSoapDto) {
        CircuitDistanceDto result = new CircuitDistanceDto();
        result.setDistanceStation(circuitSoapDto.getDistanceStation());
        result.setCircuitDto(circuitDtoMapper.soaptoDto(circuitSoapDto.getCircuitSoapDto()));
        return result;

    }
}
