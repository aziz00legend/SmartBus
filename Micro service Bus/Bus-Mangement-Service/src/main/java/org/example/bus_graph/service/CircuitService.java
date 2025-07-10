package org.example.bus_graph.service;

import org.example.bus_graph.Dto.*;
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
    @Autowired
    private StationRepository stationRepository;

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
    public Circuit createCircuitByInfo(Long busId ,CircuitDtoSave CircuitDtoS ,List<Long> stationIds,LocalTime startTime,Integer timeSpeentineveryStation) {

        Circuit circuit = new Circuit();
        circuit.setWeekend(circuit.isWeekend());
        circuit.setHolidayWork(circuit.isHolidayWork());
        circuit.setMondayToFriday(circuit.isMondayToFriday());
        circuit.setCode(CircuitDtoS.getCode());
        Bus bus = busRepository.findById(busId).get();
        bus.getCircuits().add(circuit);
        busRepository.save(bus);

        circuit.setStops(new ArrayList<>());


        if (stationIds.size() < 2 )
            return null;

        Stop stopBegin = new Stop();
        stopBegin.setStation(stationRepository.findById(stationIds.get(0)).get());
        stopBegin.setDepartureTime(startTime);
        stopBegin.setStopCode("S" +1+circuit.getCode() );

        circuit.getStops().add(stopBegin);
        circuit.setStartingStop(stopBegin);

        LocalTime arrivalTime = startTime ;

        for (int i=1; i<stationIds.size()-1; ++i){
            LocalTime departureTime = arrivalTime ;

            Route route = routeRepository.findShortestRouteBetweenStationsDirctedLinkedOnlyProprite(stationIds.get(i-1),stationIds.get(i));
            arrivalTime= arrivalTime.plusMinutes(route.getTravelTime()) ;


            if (checkRushHour(departureTime,arrivalTime))
                arrivalTime=arrivalTime.plusMinutes(route.getRushTime());

            Stop stop = new Stop();
            arrivalTime=arrivalTime.plusMinutes(timeSpeentineveryStation);
            stop.setDepartureTime(arrivalTime);
            stop.setStopCode("S" +(i+1)+circuit.getCode() );
            stop.setStation(stationRepository.findById(stationIds.get(i)).get());
            circuit.getStops().add(stop);
            stopBegin.setNextStop(stop);
            stopBegin=stop;





        }

        Stop stopEnd = new Stop();
        stopEnd.setStation(stationRepository.findById(stationIds.get(stationIds.size()-1)).get());
        stopEnd.setStopCode("S" +stationIds.size()+circuit.getCode() );
        stopBegin.setNextStop(stopEnd);
        circuit.getStops().add(stopEnd);









        return circuitRepository.save(circuit);


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



}
