package com.flight;

import com.flight.config.ConstantValue;
import com.flight.entities.Role;
import com.flight.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FlightreservationapplicationApplication implements CommandLineRunner {

//	@Autowired
//	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(FlightreservationapplicationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try
		{
			Role role = new Role();
			role.setId(ConstantValue.NORMAL_USER);
			role.setName("NORMAL_USER");

			Role role1 = new Role();
			role1.setId(ConstantValue.ADMIN_USER);
			role1.setName("ADMIN_USER");
			List<Role> roles =new ArrayList<>();
			roles.add(role);
			roles.add(role1);
			List<Role> result = roleRepo.saveAll(roles);
		}
		catch (Exception e)
		{

		}

	}
}
