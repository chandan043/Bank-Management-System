package com.gl.bms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BankManagementSystemException extends RuntimeException {
    public BankManagementSystemException(String messsage){
        super(messsage);
    }
}
