package com.flight.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departed_flights")
public class DepartedFlight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flightNumber")
    private String flightNumber;

    @Column(name = "operatingAirlines")
    private String operatingAirlines;

    @Column(name = "departureCity")
    private String departureCity;

    @Column(name = "arrivalCity")
    private String arrivalCity;

    @Column(name = "dateOfDeparture")
    private Date dateOfDeparture;

    @Column(name = "estimatedDepartureTime")
    private Timestamp estimatedDepartureTime;

    // Add any additional fields and methods as needed
}
