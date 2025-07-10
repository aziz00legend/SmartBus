package org.example.bus_graph.mapper;

import org.example.bus_graph.Dto.StopDto;
import org.example.bus_graph.Dto.SoapDto.StopSoapDto;
import org.springframework.stereotype.Service;

@Service
public class StopSoapDtoMapper {


    private final  TimeDtoMapper timeDtoMapper;

    public StopSoapDtoMapper(TimeDtoMapper timeDtoMapper) {
        this.timeDtoMapper = timeDtoMapper;
    }


    public StopSoapDto toStopSoapDto(StopDto stopDto) {
        StopSoapDto stopSoapDto = new StopSoapDto();

        stopSoapDto.setArrivalTime(timeDtoMapper.mapLocalTimeTOTimeDto(stopDto.getArrivalTime()));
        stopSoapDto.setDepartureTime(timeDtoMapper.mapLocalTimeTOTimeDto(stopDto.getDepartureTime()));
        stopSoapDto.setStationName(stopDto.getStationName());
        return stopSoapDto;
    }
    public StopDto toStopDto(StopSoapDto stopSoapDto) {
        StopDto stopDto = new StopDto();
        stopDto.setArrivalTime(timeDtoMapper.mapTimeDtoToLocalTime(stopSoapDto.getArrivalTime()));
        stopDto.setDepartureTime(timeDtoMapper.mapTimeDtoToLocalTime(stopSoapDto.getDepartureTime()));
        stopDto.setStationName(stopSoapDto.getStationName());
        return stopDto;

    }


}
