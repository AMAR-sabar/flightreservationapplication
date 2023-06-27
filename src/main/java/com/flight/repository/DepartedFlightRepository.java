package com.flight.repository;
import com.flight.entities.DepartedFlight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartedFlightRepository extends JpaRepository<DepartedFlight, Long> {
    // Add any custom query methods if required
}
