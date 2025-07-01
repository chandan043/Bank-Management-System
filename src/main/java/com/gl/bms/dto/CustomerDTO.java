package com.gl.bms.dto;

/*
    * Add the appropriate annotation for the fields to handle the validation
      using hibernate validator. Requirements are:
      * customer name should not be null and should have a minimum of 3 characters and Two words.
      * customer email should not be null or blank and should be a valid email and correct format.
      * customer phone_number not be null or blank and must be exactly 10 digits
 */

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private Integer customer_id;
    @Size(min = 3, message = "The name should be in 3 Characters minimum")
    @NotBlank(message = "The name should not be null/blank!")
    private String name;
    @NotBlank(message = "The name should not be null/blank!")
    @Email(message = "The Email should be in Vaild format")
    private String email;
    @NotBlank(message = "The contactNumber should not be NULL/Blank!")
    @Pattern(regexp = "\\d{10}",  message = "The contactNumber should be in 10 digits")
    private String contactNumber;

    @Valid
    private AccountsDTO accountsDTO;
    @Valid
    private BranchDTO branchDTO;

}
