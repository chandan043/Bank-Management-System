package com.gl.bms.dto;

/*
    * Add the appropriate annotation for the fields to handle the validation
      using hibernate validator. Requirements are:
      * branch_name should not be null or blank.
      * branch_location should not be null or blank.
      * branch_contact_number not be null or blank and must be exactly 10 digits
.
 */

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchDTO {

    private Integer branch_id;
    @NotBlank(message = "The branch_name should not be NULL / BLANK !")
    private String branch_name;
    @NotBlank(message = "The branch_location should not be NULL / BLANK !")
    private String branch_location;

    @NotNull(message = "Branch contact number is required")
    @Min(value = 1000000000L, message = "Branch contact number must be at least 10 digits")
    @Max(value = 9999999999L, message = "Branch contact number must be at most 10 digits")
    private Long branchContactNumber;

}

