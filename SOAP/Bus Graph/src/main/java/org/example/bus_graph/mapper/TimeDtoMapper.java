package org.example.bus_graph.mapper;

import org.example.bus_graph.Dto.SoapDto.TimeDto;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
@Service
public class   TimeDtoMapper {


    public LocalTime mapTimeDtoToLocalTime(TimeDto timeDto) {
        if (timeDto == null) {
            return null;
        }
        return LocalTime.of(timeDto.getHour(), timeDto.getMinute());
    }

    public TimeDto mapLocalTimeTOTimeDto(LocalTime localTime) {
        if (localTime == null) {
            return null;
        }
        TimeDto timeDto = new TimeDto();
        timeDto.setHour(localTime.getHour());
        timeDto.setMinute(localTime.getMinute());
        return timeDto;
    }


}
