package org.example.bus_graph.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.bus_graph.Dto.CircuitDistanceDto;
import org.example.bus_graph.Dto.CircuitDto;
import org.example.bus_graph.node.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TuniseWebService {


    private final CircuitService circuitService;
    @Autowired
    public TuniseWebService(CircuitService circuitService) {
        this.circuitService = circuitService;
    }



    @WebMethod
    public List<CircuitDto> getAllCircuitsBetweenStation(@WebParam(name="fromStation") String fromStation,@WebParam(name = "toStation" ) String toStation) {

        return circuitService.getAllCircuitsBetweenStation(fromStation, toStation) ;

    }

    @WebMethod
    public List<CircuitDistanceDto> getClosestStations(
            @WebParam(name = "coordinateX") float coordinateX,
            @WebParam(name = "coordinateY") float coordinateY,
            @WebParam(name = "destinationStation") String destinationStation,
            @WebParam(name = "arrivalTimeLimit") LocalTime arrivalTimeLimit,
            @WebParam(name = "earlyDepartureTime") LocalTime earlyDepartureTime,
            @WebParam(name = "departureTimeLimit") LocalTime departureTimeLimit) {

        return circuitService.closetStation(coordinateX, coordinateY, destinationStation, arrivalTimeLimit, earlyDepartureTime, departureTimeLimit);
    }

    @WebMethod
    public float getTotalCostByDay(
            @WebParam(name = "date") LocalDate date) {
        return circuitService.totalCostbyDay(date);
    }

    @WebMethod
    public boolean isCircuitActiveOnDate(
            @WebParam(name = "circuitID") Long circuitID,
            @WebParam(name = "date") LocalDate date) {
        return circuitService.isCircuitActive(circuitID, date);
    }


}
