package com.gl.bms.dto;

/*
    * Add the appropriate annotation for the fields to handle the validation
      using hibernate validator. Requirements are:
      * account_number should not be null and must have exactly 12 digits.
      * account_type should not be null or blank.
      * account_balance should not null or blank and must be positive number.
 */

import com.gl.bms.entity.Accounts;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountsDTO {

    private Integer account_id;

    @NotNull(message = "Account number must not be null")
    @Min(value = 100000000000L, message = "Account number must be a 12-digit number")
    @Max(value = 999999999999L, message = "Account number must be a 12-digit number")
    private Long account_number;

    @NotBlank(message = "The account_type should not be NULL / BLANK !")
    private String account_type;

    @NotNull(message = "The balance should not be NULL !")
    @Positive(message = "The Balance Should be in Positive Number")
    private Float balance;

}
