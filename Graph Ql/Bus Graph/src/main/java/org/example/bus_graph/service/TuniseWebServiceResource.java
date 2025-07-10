package org.example.bus_graph.service;



/*
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Path("/tunise")

public class TuniseWebServiceResource {

    private final CircuitService circuitService;

    public TuniseWebServiceResource(CircuitService circuitService) {
        this.circuitService = circuitService;
    }

    @GET
    @Path("/circuits")
    public List<CircuitDto> getAllCircuitsBetweenStation(
            @QueryParam("fromStation") String fromStation,
            @QueryParam("toStation") String toStation) {
        return circuitService.getAllCircuitsBetweenStation(fromStation, toStation);
    }

    @GET
    @Path("/closest-stations")
    public List<CircuitDistanceDto> getClosestStations(
            @QueryParam("coordinateX") float coordinateX,
            @QueryParam("coordinateY") float coordinateY,
            @QueryParam("destinationStation") String destinationStation,
            @QueryParam("arrivalTimeLimit") String arrivalTimeLimit,
            @QueryParam("earlyDepartureTime") String earlyDepartureTime,
            @QueryParam("departureTimeLimit") String departureTimeLimit) {
        return circuitService.closetStation(
                coordinateX, coordinateY, destinationStation,
                LocalTime.parse(arrivalTimeLimit),
                LocalTime.parse(earlyDepartureTime),
                LocalTime.parse(departureTimeLimit));
    }

    @GET
    @Path("/total-cost")
    public float getTotalCostByDay(@QueryParam("date") String date) {
        return circuitService.totalCostbyDay(LocalDate.parse(date));
    }

    @GET
    @Path("/circuit-status")
    public boolean isCircuitActiveOnDate(
            @QueryParam("circuitID") Long circuitID,
            @QueryParam("date") String date) {
        return circuitService.isCircuitActive(circuitID, LocalDate.parse(date));
    }
}
*/