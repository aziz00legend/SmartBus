package org.example.bus_graph.service;

import org.example.bus_graph.node.Holiday;
import org.example.bus_graph.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HolidayService {

    @Autowired
    private HolidayRepository holidayRepository;

    // Create a Holiday
    public Holiday createHoliday(Holiday holiday) {
        return holidayRepository.save(holiday);
    }

    // Retrieve a Holiday by ID
    public Optional<Holiday> getHolidayById(Long id) {
        return holidayRepository.findById(id);
    }

    // Retrieve all Holidays
    public List<Holiday> getAllHolidays() {
        return holidayRepository.findAll();
    }

    // Update an existing Holiday
    public Holiday updateHoliday(Long id, Holiday updatedHoliday) {
        return holidayRepository.findById(id)
                .map(holiday -> {
                    holiday.setStartDate(updatedHoliday.getStartDate());
                    holiday.setEndDate(updatedHoliday.getEndDate());
                    holiday.setName(updatedHoliday.getName());
                    return holidayRepository.save(holiday);
                })
                .orElseThrow(() -> new RuntimeException("Holiday not found with ID: " + id));
    }

    // Delete a Holiday
    public void deleteHoliday(Long id) {
        holidayRepository.deleteById(id);
    }
}
