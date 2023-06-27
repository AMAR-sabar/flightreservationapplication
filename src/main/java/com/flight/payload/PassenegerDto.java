package com.flight.payload;

import lombok.Data;

@Data
public class PassenegerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private int age;
    private String gender;
    private String email;
    private String phone;
}
