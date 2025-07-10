package org.example.bus_graph.mapper;

import org.example.bus_graph.Dto.CircuitDto;
import org.example.bus_graph.Dto.SoapDto.CircuitSoapDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service





public class CircuitDtoMapper {


    private final StopSoapDtoMapper stopSoapDtoMapper;

    @Autowired
    public CircuitDtoMapper(StopSoapDtoMapper stopSoapDtoMapper) {
        this.stopSoapDtoMapper = stopSoapDtoMapper;
    }

    public CircuitSoapDto dtoToSoap(CircuitDto circuitDto) {
        CircuitSoapDto circuitSoapDto = new CircuitSoapDto();
        circuitSoapDto.setBusDto(circuitDto.getBusDto());
        circuitSoapDto.setDistance(circuitDto.getDistance());
        circuitSoapDto.setCode(circuitDto.getCode());
        circuitSoapDto.setStopSoapDtos(circuitDto.getStopDtos().stream().map(stopSoapDtoMapper::toStopSoapDto).toList());
        circuitSoapDto.setPrice(circuitDto.getPrice());
        circuitSoapDto.setDuration(circuitDto.getDuration());
        circuitSoapDto.setWeekend(circuitDto.isWeekend());
        circuitSoapDto.setHolidayWork(circuitDto.isHolidayWork());
        circuitSoapDto.setMondayToFriday(circuitDto.isMondayToFriday());
        return circuitSoapDto;

    }
    public CircuitDto soaptoDto(CircuitSoapDto circuitSoapDto) {
        CircuitDto circuitDto = new CircuitDto();
        circuitDto.setBusDto(circuitSoapDto.getBusDto());
        circuitDto.setDistance(circuitSoapDto.getDistance());
        circuitDto.setCode(circuitSoapDto.getCode());
        circuitDto.setStopDtos(circuitSoapDto.getStopSoapDtos().stream().map(stopSoapDtoMapper::toStopDto).toList());
        circuitDto.setPrice(circuitSoapDto.getPrice());
        circuitDto.setDuration(circuitSoapDto.getDuration());
        circuitDto.setWeekend(circuitSoapDto.isWeekend());
        circuitDto.setHolidayWork(circuitSoapDto.isHolidayWork());
        circuitDto.setMondayToFriday(circuitSoapDto.isMondayToFriday());
        return circuitDto;

    }
}
