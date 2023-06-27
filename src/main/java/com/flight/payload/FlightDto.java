package com.flight.payload;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
@Data
public class FlightDto {
        private String flightNumber;
        private String operatingAirlines;
        private String departureCity;
        private String arrivalCity;
        private Date dateOfDeparture;
        private Timestamp estimatedDepartureTime;
}
