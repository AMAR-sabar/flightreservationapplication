package com.flight.repository;

import com.flight.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface FlightRepo extends JpaRepository<Flight,String> {
    List<Flight> findByDateOfDepartureAfter(Date currentDate);
}
