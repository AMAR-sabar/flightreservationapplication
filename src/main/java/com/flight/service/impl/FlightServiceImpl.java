package com.flight.service.impl;
import com.flight.entities.DepartedFlight;
import com.flight.entities.Flight;
import com.flight.exceptions.ResourceNotFoundException;
import com.flight.repository.DepartedFlightRepository;
import com.flight.repository.FlightRepo;
import com.flight.service.FlightService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightRepo flightRepo;

    @Autowired
    private DepartedFlightRepository departedFlightRepository;


    public Flight getFlightByFlightNumber(String flightNumber) {
        return flightRepo.findById(flightNumber).orElseThrow((()->new ResourceNotFoundException("FlightNumber",flightNumber)));
    }

    public List<Flight> getUpcomingFlights() {
        Date currentDate = new Date();
        return flightRepo.findByDateOfDepartureAfter(currentDate);
    }

    public Flight saveFlight(Flight flight) {
        return flightRepo.save(flight);
    }

    public void deleteFlight(String flightNumber) {
        flightRepo.deleteById(flightNumber);
    }

    public void departFlight(String flightNumber) {
        // Retrieve the flight from the flight repository
        Flight flight = flightRepo.findById(flightNumber)
                .orElseThrow(() -> new ResourceNotFoundException("FlightNumber", flightNumber));

        // Create a new DepartedFlight object and populate it with the flight details
        DepartedFlight departedFlight = new DepartedFlight();
        departedFlight.setFlightNumber(flight.getFlightNumber());
        departedFlight.setOperatingAirlines(flight.getOperatingAirlines());
        departedFlight.setDepartureCity(flight.getDepartureCity());
        departedFlight.setArrivalCity(flight.getArrivalCity());
        departedFlight.setDateOfDeparture(flight.getDateOfDeparture());
        departedFlight.setEstimatedDepartureTime(flight.getEstimatedDepartureTime());

        // Save the departed flight to the departed_flights table
        departedFlightRepository.save(departedFlight);

        // Delete the flight from the flights table
        flightRepo.deleteById(flightNumber);
    }
}
