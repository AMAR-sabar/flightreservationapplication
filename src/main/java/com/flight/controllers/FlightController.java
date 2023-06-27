package com.flight.controllers;

import com.flight.entities.Flight;
import com.flight.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fly")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    //Get Flight by Flight Number
    @GetMapping("/flight/{flightNumber}")
    public ResponseEntity<Flight> getFlightByFlightNumber(@PathVariable String flightNumber) {
        Flight flight = flightService.getFlightByFlightNumber(flightNumber);
        if (flight != null) {
            return new ResponseEntity<>(flight, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //All Get Flight list
    @GetMapping("/all")
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightService.getUpcomingFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }
    //Add Flight Details
    @PostMapping("/add")
    public ResponseEntity<Flight> saveFlight(@RequestBody Flight flight) {
        Flight savedFlight = flightService.saveFlight(flight);
        return new ResponseEntity<>(savedFlight, HttpStatus.CREATED);
    }
    //Delete Flight Details
    @DeleteMapping("/delete/{flightNumber}")
    public ResponseEntity<String> deleteFlight(@PathVariable String flightNumber) {
        flightService.deleteFlight(flightNumber);
        return new ResponseEntity<>("Flight Deleted Successfully",HttpStatus.OK);
    }

    @PostMapping("/{flightNumber}/depart")
    public ResponseEntity<String> departFlight(@PathVariable String flightNumber) {
        flightService.departFlight(flightNumber);
        return ResponseEntity.ok("Flight departed successfully");
    }
}
