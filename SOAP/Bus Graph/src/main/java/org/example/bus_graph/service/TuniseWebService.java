package org.example.bus_graph.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.bus_graph.Dto.CircuitDistanceDto;
import org.example.bus_graph.Dto.CircuitDto;
import org.example.bus_graph.Dto.SoapDto.CircuitDistanceSoapDto;
import org.example.bus_graph.Dto.SoapDto.CircuitSoapDto;
import org.example.bus_graph.Dto.SoapDto.DateDto;
import org.example.bus_graph.Dto.SoapDto.TimeDto;
import org.example.bus_graph.mapper.CircuitDistanceDtoMapper;
import org.example.bus_graph.mapper.CircuitDtoMapper;
import org.example.bus_graph.mapper.DateDtoMapper;
import org.example.bus_graph.mapper.TimeDtoMapper;
import org.example.bus_graph.node.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@WebService(serviceName = "TuniseWebService")
public class TuniseWebService {


    private final CircuitService circuitService;
    private final CircuitDtoMapper circuitDtoMapper;
    private final DateDtoMapper dateDtoMapper;
    private final TimeDtoMapper timeDtoMapper;
   private final CircuitDistanceDtoMapper distanceDtoMapper;
    @Autowired
    public TuniseWebService(CircuitService circuitService, CircuitDtoMapper circuitDtoMapper,
                            DateDtoMapper dateDtoMapper, TimeDtoMapper timeDtoMapper, CircuitDistanceDtoMapper distanceDtoMapper) {
        this.circuitService = circuitService;
        this.circuitDtoMapper = circuitDtoMapper;



        this.dateDtoMapper = dateDtoMapper;
        this.timeDtoMapper = timeDtoMapper;
        this.distanceDtoMapper = distanceDtoMapper;
    }





    @WebMethod
    public List<CircuitSoapDto> getAllCircuitsBetweenStation(@WebParam(name="fromStation") String fromStation,
                                                             @WebParam(name = "toStation" ) String toStation) {

        return circuitService.getAllCircuitsBetweenStation(fromStation, toStation).stream().map(circuitDtoMapper::dtoToSoap).toList() ;

    }

    @WebMethod
    public List<CircuitDistanceSoapDto> getClosestStations(
            @WebParam(name = "coordinateX") float coordinateX,
            @WebParam(name = "coordinateY") float coordinateY,
            @WebParam(name = "destinationStation") String destinationStation,
            @WebParam(name = "arrivalTimeLimit") TimeDto arrivalTimeLimit,
            @WebParam(name = "earlyDepartureTime") TimeDto earlyDepartureTime,
            @WebParam(name = "departureTimeLimit") TimeDto departureTimeLimit) {

        return circuitService.closetStation(
                        coordinateX, coordinateY, destinationStation,
                        timeDtoMapper.mapTimeDtoToLocalTime(arrivalTimeLimit) ,
                        timeDtoMapper.mapTimeDtoToLocalTime(earlyDepartureTime),
                        timeDtoMapper.mapTimeDtoToLocalTime(departureTimeLimit))
                .stream().map(distanceDtoMapper::dtotoSoap).toList();
    }
    @WebMethod
    public String afficherCircuitSoapDto (@WebParam CircuitSoapDto circuitSoapDto) {
        return (circuitSoapDto.toString()) ;
    }
    @WebMethod
    public String afficherCircuitDistanceSoapDto (@WebParam CircuitDistanceSoapDto circuitDistanceSoapDto) {
        return (circuitDistanceSoapDto.toString()) ;
    }

    @WebMethod
    public String afficherDateDto (@WebParam DateDto dateDto) {
        return (dateDto.toString()) ;
    }




    @WebMethod
    public float getTotalCostByDay(
            @WebParam(name = "date") DateDto date) {
        return circuitService.totalCostbyDay(dateDtoMapper.mapDateDtoToLocalDate(date));
    }

    @WebMethod
    public boolean isCircuitActiveOnDate(
            @WebParam(name = "circuitID") Long circuitID,
            @WebParam(name = "date") DateDto date) {
        return circuitService.isCircuitActive(circuitID, dateDtoMapper.mapDateDtoToLocalDate(date));
    }


}
