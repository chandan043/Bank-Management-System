package com.gl.bms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
    * Add the appropriate annotation for the fields to handle the validation
      using hibernate validator. Requirements are:
      * transaction_type should not be null or blank.
      * transaction_amount should not null or blank and must be positive number.

 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionsDTO {

    private Integer id;

    @NotBlank(message = "The transaction_type should not be NULL / BLANK !")
    private String transaction_type;
    @NotNull(message = "The transaction_amount should not be NULL !")
    @Positive(message = "The transaction_amount Should be in Positive Number")
    private Float transaction_amount;
}
