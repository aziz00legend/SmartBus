package org.example.bus_graph.mapper;

import org.example.bus_graph.Dto.SoapDto.DateDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class DateDtoMapper {

    public LocalDate mapDateDtoToLocalDate(DateDto dateDto) {
        if (dateDto == null) {
            return null;
        }

        return LocalDate.of(dateDto.getYear(), dateDto.getMonth(), dateDto.getDay());
    }

    public DateDto mapLocalDateToDateDto(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        DateDto dateDto = new DateDto();
        dateDto.setYear(localDate.getYear());
        dateDto.setMonth(localDate.getMonthValue());
        dateDto.setDay(localDate.getDayOfMonth());
        return dateDto;
    }
}
