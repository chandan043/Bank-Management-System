package com.gl.bms.repository;

import com.gl.bms.entity.Customers;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;

// Make this into a Customer repository
public interface CustomerRepository extends JpaRepository<Customers,Long> {

    Customers findByEmail(@NotBlank(message = "The name should not be null/blank!") @Email(message = "The Email should be in Vaild format") String email);

//    Customers findByContactNumber(@NotBlank(message = "The phone_phone should not be NULL/Blank!") @Pattern(regexp = "\\d{10}",  message = "The account_number should be in 10 digits") String phone);
}
