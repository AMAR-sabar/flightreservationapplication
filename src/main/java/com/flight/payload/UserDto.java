package com.flight.payload;

import com.flight.entities.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Data
public class UserDto {
    private int id;
    @NotEmpty
    @Size(min=4 , max = 15, message = "User name should be in between 4 to 15 chars !!!")
    private String firstName;

    private String lastName;

    private String dateOfBirth;

    private String gender;


    private String country;


    private String state;


    private String postalCode;


    private String address;

    @NotEmpty
    @Email(message = "Invalid email format !!!")
    private String email;
    @NotEmpty
    @Size(min = 3, max = 10, message = "Password should be in between 8-12 chars !!!")
    private String password;
   // @Size(max = 10, message = "only 100 chars !!!")
    private Long mobile;
    private Set<Role> roles = new HashSet<>();
}