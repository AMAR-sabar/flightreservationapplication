package com.flight.service;

import com.flight.entities.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component
public class FlightScheduler {
    private final FlightService flightService;
    private final FlightTrackingService flightTrackingService;

    @Autowired
    public FlightScheduler(FlightService flightService, FlightTrackingService flightTrackingService) {
        this.flightService = flightService;
        this.flightTrackingService = flightTrackingService;
    }

    @Scheduled(fixedDelay = 60000) // Run every minute
    public void checkDepartureStatus() {
        Date currentDate = new Date();
        List<Flight> flights = flightService.getUpcomingFlights();

        for (Flight flight : flights) {
            Timestamp departureTime = flight.getEstimatedDepartureTime();
            if (currentDate.after(departureTime) && flightTrackingService.hasFlightDeparted(flight.getFlightNumber())) {
                flightService.departFlight(flight.getFlightNumber());
            }
        }

    }
}