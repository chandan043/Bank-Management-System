package com.gl.bms.service;

import com.gl.bms.dto.CustomerDTO;
import com.gl.bms.dto.ResponseDTO;
import com.gl.bms.dto.TransactionsDTO;
import java.util.List;

public interface BankManagementSystemService {

    // Change return type accordingly if needed.
    public ResponseDTO addCustomerAndOpenBankAccount(CustomerDTO customerDTO);

    // Change return type accordingly if needed.
    public ResponseDTO depositMoney(String email, Long account_number, Float amount);

    // Change return type accordingly if needed.
    public ResponseDTO withdrawMoney(String email,Long account_number, Float amount);

    // Change return type accordingly if needed.
    public List<TransactionsDTO> fetchTransactions(Long account_number);

    // Change return type accordingly if needed.
    public ResponseDTO addTransaction(Long account_number, TransactionsDTO transactionsDTO);

    // Change return type accordingly if needed.
    public ResponseDTO fetchBalance(String email);

    // Change return type accordingly if needed.
    public ResponseDTO deleteAccount(String email);

    // Change return type accordingly if needed.
    public  CustomerDTO getCustomerDetailsByEmail(String email);

}
