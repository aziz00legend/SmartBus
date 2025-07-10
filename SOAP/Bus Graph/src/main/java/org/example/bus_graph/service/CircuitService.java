package org.example.bus_graph.service;

import org.example.bus_graph.Dto.BusDto;
import org.example.bus_graph.Dto.CircuitDistanceDto;
import org.example.bus_graph.Dto.CircuitDto;
import org.example.bus_graph.Dto.StopDto;
import org.example.bus_graph.node.Bus;
import org.example.bus_graph.node.Circuit;
import org.example.bus_graph.node.Holiday;
import org.example.bus_graph.node.Stop;
import org.example.bus_graph.relationship.Route;
import org.example.bus_graph.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CircuitService {

    private final CircuitRepository circuitRepository;

    public CircuitService(CircuitRepository circuitRepository) {
        this.circuitRepository = circuitRepository;
    }

    @Autowired
    private StopRepository stopRepository;
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private HolidayRepository holidayRepository;

    // Create a new Circuit
    public Circuit createCircuit(Circuit circuit) {
        return circuitRepository.save(circuit);
    }
    public float totalCostbyDay(LocalDate date) {
        final float[] somme = {0}; // Use a mutable container for the sum


        List<Bus> busList = busRepository.findAll();
        busList.forEach(bus -> {
            float costMetre = bus.getConsommationParM(); // Cast to float if needed
            bus.getCircuits().forEach(circuit -> {
                if (isCircuitActive(circuit.getId(), date)) {
                    Stop curr = circuit.getStartingStop();
                    while (curr != null && curr.getNextStop() != null) {
                        Route route = routeRepository.findShortestRouteBetweenStationsDirctedLinkedOnlyProprite(curr.getStation().getId(), curr.getNextStop().getStation().getId());
                        if (route != null) {
                            somme[0] += costMetre * route.getDistance(); // Cast route.getDistance() to float if needed
                        }
                        curr = curr.getNextStop();
                    }
                }


            });
        });

        return somme[0];
    }



    public boolean isCircuitActive(Long circuitID, LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        Circuit circuit = circuitRepository.findById(circuitID).get();
        List<Holiday> holidays =holidayRepository.findAll();




        // Check if the circuit is active during holidays
        boolean isHoliday = holidays.stream()
                .anyMatch(holiday ->
                        date.isAfter(holiday.getStartDate()) &&
                                date.isBefore(holiday.getEndDate()) && holiday.getCircuits().stream().anyMatch(circuit1 ->  circuit.isHolidayWork() || circuit1.getId()==circuit.getId())
                );

        if (isHoliday) {
            return
                    circuit.isHolidayWork(); // Circuit works only if it allows holiday work
        }

        // Check for weekdays or weekends
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return circuit.isWeekend(); // Circuit works on weekends
        } else {
            return circuit.isMondayToFriday(); // Circuit works Monday to Friday
        }
    }













    public Optional<Circuit> getCircuitById(Long id) {
        return circuitRepository.findById(id);
    }

    // Retrieve all Circuits
    public List<Circuit> getAllCircuits() {
        return circuitRepository.findAll();
    }

    // Update an existing Circuit
    public Circuit updateCircuit(Long id, Circuit updatedCircuit) {
        return circuitRepository.findById(id)
                .map(circuit -> {
                    circuit.setWeekend(updatedCircuit.isWeekend());
                    circuit.setHolidayWork(updatedCircuit.isHolidayWork());
                    circuit.setMondayToFriday(updatedCircuit.isMondayToFriday());
                    circuit.setCode(updatedCircuit.getCode());
                    return circuitRepository.save(circuit);
                })
                .orElseThrow(() -> new RuntimeException("Circuit not found with ID: " + id));
    }

    // Delete a Circuit by ID
    public void deleteCircuit(Long id) {
        circuitRepository.deleteById(id);
    }

    // Add a Stop to a Circuit
    public Circuit addStopToCircuit(Long circuitId, Long stopId) {
        Circuit circuit = circuitRepository.findById(circuitId)
                .orElseThrow(() -> new RuntimeException("Circuit not found with ID: " + circuitId));

        Stop stop = stopRepository.findById(stopId)
                .orElseThrow(() -> new RuntimeException("Stop not found with ID: " + stopId));

        circuit.getStops().add(stop);
        return circuitRepository.save(circuit);
    }

    // Set the starting Stop for a Circuit
    public Circuit setStartingStop(Long circuitId, Long stopId) {
        Circuit circuit = circuitRepository.findById(circuitId)
                .orElseThrow(() -> new RuntimeException("Circuit not found with ID: " + circuitId));

        Stop stop = stopRepository.findById(stopId)
                .orElseThrow(() -> new RuntimeException("Stop not found with ID: " + stopId));

        circuit.setStartingStop(stop);
        return circuitRepository.save(circuit);
    }


    public List<CircuitDistanceDto>  closetStation(float myCoordinateX , float myCoordinateY,String toStation,LocalTime timeArrivalLimite,LocalTime timeDepartureEarly,LocalTime timeDepartureLimite) {
        List<Circuit> Circuits = circuitRepository.findCircuitsPassingOrEndingAtStation(toStation) ;
        for (Circuit circuit : Circuits) {
            System.out.println(circuit.getCode()
            );
        }

        Circuits =Circuits.stream().filter(circuit ->{
            circuit=circuitRepository.findById(circuit.getId()).get();




            Stop curr =circuit.getStartingStop();
            LocalTime arrival =null;

            while (! curr.getStation().getName().equals(toStation) ) {



                Route route =routeRepository.findShortestRouteBetweenStationsDirctedLinkedOnlyProprite(curr.getStation().getId(), curr.getNextStop().getStation().getId());


                arrival=curr.getDepartureTime().plusMinutes(route.getTravelTime());

                if (checkRushHour(curr.getDepartureTime(),arrival))
                    arrival=arrival.plusMinutes(route.getRushTime());

                curr = curr.getNextStop();
            }

            return arrival.isBefore(timeArrivalLimite);

        }).toList();


        List<CircuitDistanceDto> circuitDistanceDtos = new ArrayList<>();



        Circuits.stream().forEach(circuit -> {

            circuit=circuitRepository.findById(circuit.getId()).get();


            Stop stop = circuit.getStartingStop();


            CircuitDistanceDto circuitDistanceDto = null;

            while (!stop.getStation().getName().equals(toStation)) {


                if (timeDepartureLimite.isAfter(stop.getDepartureTime()) && timeDepartureEarly.isBefore(stop.getDepartureTime()))

                {

                float distance =(calculateDistance(myCoordinateX,myCoordinateY,stop.getStation().getCoordinateX(),stop.getStation().getCoordinateY()));
                if (circuitDistanceDto==null || distance < circuitDistanceDto.getDistanceStation()) {
                    circuitDistanceDto = new CircuitDistanceDto();
                    circuitDistanceDto.setDistanceStation(distance);
                    CircuitDto circuitDto = new CircuitDto();
                    System.out.println(circuit.getCode());
                    circuitDto.setCode(circuit.getCode());
                    circuitDto.setWeekend(circuit.isWeekend());
                    circuitDto.setHolidayWork(circuit.isHolidayWork());
                    circuitDto.setMondayToFriday(circuit.isMondayToFriday());
                    Bus bus = busRepository.findBusByCircuitId(circuit.getId()).get(0);
                    circuitDto.setBusDto(new BusDto());
                    circuitDto.getBusDto().setCapacite(bus.getCapacite());
                    circuitDto.getBusDto().setType(bus.getType());
                    Stop stopBegin = stop;

                    float distanceC = 0f;
                    float prixM = bus.getPrixParM();

                    LocalTime begin = stopBegin.getDepartureTime();

                    LocalTime arrival = null;
                    while (!stopBegin.getStation().getName().equals(toStation)) {

                        StopDto stopDto = new StopDto();
                        stopDto.setStationName(stopBegin.getStation().getName());
                        stopDto.setDepartureTime(stopBegin.getDepartureTime());

                        Long idStationdeparture = stopBegin.getStation().getId();
                        long idStationNext = stopBegin.getNextStop().getStation().getId();
                        Route route = routeRepository.findShortestRouteBetweenStationsDirctedLinkedOnlyProprite(idStationdeparture, idStationNext);
                        stopDto.setArrivalTime(arrival);
                        arrival = stopDto.getDepartureTime().plusMinutes(route.getTravelTime());


                        if (checkRushHour(stopDto.getDepartureTime(), arrival))
                            arrival = arrival.plusMinutes(route.getRushTime());
                        distanceC += route.getDistance();


                        circuitDto.getStopDtos().add(stopDto);
                        stopBegin = stopBegin.getNextStop();

                    }
                    StopDto stopDto = new StopDto();
                    stopDto.setStationName(stopBegin.getStation().getName());
                    stopDto.setArrivalTime(arrival);
                    circuitDto.getStopDtos().add(stopDto);


                    circuitDto.setDistance(distanceC);
                    circuitDto.setPrice(distanceC * prixM);
                    circuitDto.setDuration(Duration.between(begin, arrival).toMinutes());
                    circuitDistanceDto.setCircuitDto(circuitDto);
                    stop = stop.getNextStop();
                }
                  else
                    stop=stop.getNextStop();
                }
                else
                    stop=stop.getNextStop();
                }





            circuitDistanceDtos.add(circuitDistanceDto);


        });


        return circuitDistanceDtos;






    }


    public List<CircuitDto> getAllCircuitsBetweenStation(String fromStation, String toStation) {

        List<Circuit> circuits = new ArrayList<>();
        circuits=circuitRepository.findCircuitsContainingSequence(fromStation, toStation);
        List<CircuitDto> circuitDtos = new ArrayList<>();
        circuits.stream().forEach(circuit ->
        {
            circuit=circuitRepository.findById(circuit.getId()).get();

            CircuitDto circuitDto = new CircuitDto();
            circuitDto.setCode(circuit.getCode());
            circuitDto.setWeekend(circuit.isWeekend());
            circuitDto.setHolidayWork(circuit.isHolidayWork());
            circuitDto.setMondayToFriday(circuit.isMondayToFriday());
            Bus bus = busRepository.findBusByCircuitId(circuit.getId()).get(0) ;
            circuitDto.setBusDto(new BusDto()) ;
            circuitDto.getBusDto().setCapacite(bus.getCapacite());
            circuitDto.getBusDto().setType(bus.getType());
            Stop stopBegin =circuit.getStartingStop();


            while (! stopBegin.getStation().getName().equals(fromStation) )
            {
                stopBegin=stopBegin.getNextStop();
            }

            float distance = 0 ;
            float prixM= bus.getPrixParM();
            LocalTime begin =stopBegin.getDepartureTime();

            LocalTime arrival = null;
            while (!stopBegin.getStation().getName().equals(toStation) )
            {

                StopDto stopDto =new StopDto();
                stopDto.setStationName(stopBegin.getStation().getName());
                stopDto.setDepartureTime(stopBegin.getDepartureTime());

                Long idStationdeparture =stopBegin.getStation().getId();
                long idStationNext = stopBegin.getNextStop().getStation().getId();
                Route route =routeRepository.findShortestRouteBetweenStationsDirctedLinkedOnlyProprite(idStationdeparture, idStationNext);
                stopDto.setArrivalTime(arrival );
                arrival=stopDto.getDepartureTime().plusMinutes(route.getTravelTime());


                if (checkRushHour(stopDto.getDepartureTime(),arrival))
                    arrival=arrival.plusMinutes(route.getRushTime());
                distance += route.getDistance();



                circuitDto.getStopDtos().add(stopDto);
                stopBegin=stopBegin.getNextStop();

            }
            StopDto stopDto =new StopDto();
            stopDto.setStationName(stopBegin.getStation().getName());
            stopDto.setArrivalTime(arrival );
            circuitDto.getStopDtos().add(stopDto);



            circuitDto.setDistance(distance);
            circuitDto.setPrice(distance*prixM);
            circuitDto.setDuration(Duration.between(begin,arrival).toMinutes());

            circuitDtos.add(circuitDto);







        });


        return circuitDtos;
    }

    private boolean checkRushHour(LocalTime departureTime, LocalTime arrivalTime) {
        // Define rush hour periods
        LocalTime morningStart = LocalTime.of(7, 30);
        LocalTime morningEnd = LocalTime.of(9, 0);
        LocalTime middayStart = LocalTime.of(12, 0);
        LocalTime middayEnd = LocalTime.of(13, 30);
        LocalTime eveningStart = LocalTime.of(17, 0);
        LocalTime eveningEnd = LocalTime.of(18, 0);

        // Check if the given time range overlaps with any rush hour period
        if (overlaps(departureTime, arrivalTime, morningStart, morningEnd) ||
                overlaps(departureTime, arrivalTime, middayStart, middayEnd) ||
                overlaps(departureTime, arrivalTime, eveningStart, eveningEnd)) {
            return true;
        }
        return false;
    }

    // Helper method to check if two time ranges overlap
    private boolean overlaps(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2) {
        return !start1.isAfter(end2) && !end1.isBefore(start2);
    }


    private float calculateDistance(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

}
