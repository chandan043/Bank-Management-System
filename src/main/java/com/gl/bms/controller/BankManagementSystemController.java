package com.gl.bms.controller;


import com.gl.bms.dto.CustomerDTO;
import com.gl.bms.dto.ResponseDTO;
import com.gl.bms.dto.TransactionsDTO;
import com.gl.bms.service.BankManagementSystemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BankManagementSystemController {

    @Autowired
    BankManagementSystemService bankManagementSystemService;

    @PostMapping("/add-customer-and-open-bank-account")
    public ResponseEntity<ResponseDTO> addCustomerAndOpenBankAccount(@RequestBody @Valid CustomerDTO customerDTO) {
        ResponseDTO responseDTO = bankManagementSystemService.addCustomerAndOpenBankAccount(customerDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/deposit-money/{email}/{account_number}/{amount}")
    public ResponseEntity<ResponseDTO> depositMoney(@PathVariable String email,@PathVariable Long account_number,@PathVariable Float amount) {
        ResponseDTO responseDTO = bankManagementSystemService.depositMoney(email, account_number, amount);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/withdraw-money/{email}/{account_number}/{amount}")
    public ResponseEntity<ResponseDTO> withdrawMoney(@PathVariable String email,@PathVariable Long account_number,@PathVariable Float amount) {
        ResponseDTO responseDTO = bankManagementSystemService.withdrawMoney(email, account_number, amount);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/fetch-transactions/{account_number}")
    public ResponseEntity<List<TransactionsDTO>> fetchTransactions(@PathVariable Long account_number) {
        List<TransactionsDTO> transactionsDTOS = bankManagementSystemService.fetchTransactions(account_number);
        return ResponseEntity.ok(transactionsDTOS);
    }
    @GetMapping("/fetch-balance/{email}")
    public ResponseEntity<ResponseDTO> fetchBalance(@PathVariable String email) {
        ResponseDTO balance = bankManagementSystemService.fetchBalance(email);
        return new ResponseEntity<>(balance,HttpStatus.OK);
    }

    @DeleteMapping("/delete-account/{email}")
    public ResponseEntity<ResponseDTO> deleteAccount(@PathVariable String email) {
        ResponseDTO responseDTO = bankManagementSystemService.deleteAccount(email);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/add-transaction/{accountNumber}")
    public ResponseEntity<ResponseDTO> addTransaction(@PathVariable Long accountNumber,@RequestBody @Valid TransactionsDTO transactionsDTO) {
        ResponseDTO responseDTO = bankManagementSystemService.addTransaction(accountNumber,transactionsDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/get-customer-details-by-email/{email}")
    public ResponseEntity<CustomerDTO> getCustomerDetailsByEmail(@PathVariable String email) {
        CustomerDTO customerDTO = bankManagementSystemService.getCustomerDetailsByEmail(email);
        return new ResponseEntity<>(customerDTO,HttpStatus.OK);
    }

}
