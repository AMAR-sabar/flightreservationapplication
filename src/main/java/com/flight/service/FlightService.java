package com.flight.service;

import com.flight.entities.Flight;

import java.util.List;

public interface FlightService {
    public Flight getFlightByFlightNumber(String flightNumber);

    public List<Flight> getUpcomingFlights();

    public Flight saveFlight(Flight flight);

    public void deleteFlight(String flightNumber);

    void departFlight(String flightNumber);
}
