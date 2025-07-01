package com.gl.bms.service;

import com.gl.bms.dto.*;
import com.gl.bms.entity.Accounts;
import com.gl.bms.entity.Branchs;
import com.gl.bms.entity.Customers;
import com.gl.bms.entity.Transactions;
import com.gl.bms.exception.BankManagementSystemException;
import com.gl.bms.repository.AccountsRepository;
import com.gl.bms.repository.BranchRepository;
import com.gl.bms.repository.CustomerRepository;
import com.gl.bms.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BankManagementSystemServiceImpl implements BankManagementSystemService {

    @Autowired
    TransactionsRepository transactionsRepository;
    @Autowired
    AccountsRepository accountsRepository;
    @Autowired
    BranchRepository branchRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public ResponseDTO addCustomerAndOpenBankAccount(CustomerDTO customerDTO) {
        Customers existingCustomer = customerRepository.findByEmail(customerDTO.getEmail());
        if (existingCustomer != null) {
            throw new BankManagementSystemException("The customer already exists with email ID: " + customerDTO.getEmail());
        }

        AccountsDTO accountsDTO = customerDTO.getAccountsDTO();
        Accounts newAccount = new Accounts();

        newAccount.setAccount_balance(accountsDTO.getBalance());
        newAccount.setAccountNumber(accountsDTO.getAccount_number());
        newAccount.setAccount_type(accountsDTO.getAccount_type());

        BranchDTO branchDTO = customerDTO.getBranchDTO();
        Branchs branch = new Branchs();
        branch.setBranch_name(branchDTO.getBranch_name());
        branch.setBranch_location(branchDTO.getBranch_location());
        branch.setBranch_contact_number(branchDTO.getBranchContactNumber());

        newAccount.setBranch(branch);
        branch.getAccountsList().add(newAccount);

        Customers newCustomer = new Customers(
                null,
                customerDTO.getName(),
                customerDTO.getEmail(),
                customerDTO.getContactNumber(),
                newAccount
        );
        newAccount.setCustomer(newCustomer);

        branchRepository.save(branch);
        accountsRepository.save(newAccount);
        customerRepository.save(newCustomer);

        return new ResponseDTO("The customer was added and a bank account opened successfully.");
    }

    @Override
    public ResponseDTO depositMoney(String email, Long account_number, Float amount) {
        if (amount <= 0) {
            throw new BankManagementSystemException("Deposit amount must be greater than zero.");
        }
        Customers customers = customerRepository.findByEmail(email);
        if(customers==null) throw new BankManagementSystemException("The Customer Does not Exists with email id:"+email);
        Accounts account =customers.getAccount();
        if(!account.getAccountNumber().equals(account_number)){
            throw new BankManagementSystemException("the Customer does not have account with account number:"+account_number);
        }

        float newBalance = account.getAccount_balance() + amount;
        account.setAccount_balance(newBalance);
        accountsRepository.save(account);

        return new ResponseDTO("Deposit successful. New balance: ₹" + newBalance);
    }


    @Override
    public ResponseDTO withdrawMoney(String email, Long account_number, Float amount) {
        // Validate input
        if (amount <= 0) {
            throw new BankManagementSystemException("Withdrawal amount must be greater than zero.");
        }

        Customers customers = customerRepository.findByEmail(email);
        if(customers==null) throw new BankManagementSystemException("The Customer Does not Exists with email id:"+email);
        Accounts account =customers.getAccount();
        if(!account.getAccountNumber().equals(account_number)){
            throw new BankManagementSystemException("the Customer does not have account with account number:"+account_number);
        }

        // Check sufficient balance
        if (account.getAccount_balance() < amount) {
            throw new BankManagementSystemException("Insufficient balance.");
        }

        // Perform withdrawal
        float newBalance = account.getAccount_balance() - amount;
        account.setAccount_balance(newBalance);
        accountsRepository.save(account);

        return new ResponseDTO("Withdrawal successful. New balance: ₹" + newBalance);
    }


    @Override
    public List<TransactionsDTO> fetchTransactions(Long account_number) {

        Accounts account = accountsRepository.findByAccountNumber(account_number);
        if (account== null) throw new BankManagementSystemException("The Account does Not Exists with Account Number:"+ account_number);
        List<TransactionsDTO> transactionsDTOS=new ArrayList<>();
        for(Transactions transactions : account.getTransactionsList()){
            TransactionsDTO transactionsDTO = new TransactionsDTO(
                    transactions.getTransaction_id(),
                    transactions.getTransaction_type(),
                    transactions.getTransaction_amount()
            );
            transactionsDTOS.add(transactionsDTO);
        }
        return transactionsDTOS;
    }

    @Override
    public ResponseDTO fetchBalance(String email) {
        Customers customers = customerRepository.findByEmail(email);
        if(customers==null) throw new BankManagementSystemException("The Customer Does not Exists with email id:"+email);
        Accounts account =customers.getAccount();
        if (account== null) throw new BankManagementSystemException("The Customer Does not have any Account with email id:"+email);
        Float balance = account.getAccount_balance();
        return new ResponseDTO("The Current balance is :"+ balance);
    }

    @Override
    public ResponseDTO deleteAccount(String email) {
        Customers customers = customerRepository.findByEmail(email);
        if(customers==null) throw new BankManagementSystemException("The Customer Does not Exists with email id:"+email);
        Accounts account =customers.getAccount();
        if (account== null) throw new BankManagementSystemException("The Customer Does not have any Account with email id:"+email);
        accountsRepository.delete(account);
        return new ResponseDTO("The Account associated with email id:"+email +" was Sucessfully Deleted");
    }

    @Override
    public ResponseDTO addTransaction(Long accountNumber, TransactionsDTO transactionsDTO) {
        Accounts account = accountsRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new BankManagementSystemException("The Account Does not Exists with this account number :"+accountNumber);
        }

        Transactions transaction = new Transactions();
        transaction.setAccount(account);
        transaction.setTransaction_amount(transactionsDTO.getTransaction_amount());
        transaction.setTransaction_type(transactionsDTO.getTransaction_type());

        transactionsRepository.save(transaction);

        if (transactionsDTO.getTransaction_type().equalsIgnoreCase("DEBIT")) {
            account.setAccount_balance(account.getAccount_balance() - transactionsDTO.getTransaction_amount());
        } else if (transactionsDTO.getTransaction_type().equalsIgnoreCase("CREDIT")) {
            account.setAccount_balance(account.getAccount_balance() + transactionsDTO.getTransaction_amount());
        }
        accountsRepository.save(account);
        return new ResponseDTO("Successfully  Transaction added successfully");
    }


    @Override
    public CustomerDTO getCustomerDetailsByEmail(String email) {
        Customers customer = customerRepository.findByEmail(email);
        if(customer==null) throw new BankManagementSystemException("The Customer Does not Exists with email id:"+email);

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomer_id(customer.getCustomer_id());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setContactNumber(customer.getContactNumber());

        AccountsDTO accountsDTO = new AccountsDTO(
                customer.getAccount().getAccount_id(),
                customer.getAccount().getAccountNumber(),
                customer.getAccount().getAccount_type(),
                customer.getAccount().getAccount_balance()
        );
        customerDTO.setAccountsDTO(accountsDTO);
        Branchs branch = customer.getAccount().getBranch();
        BranchDTO branchDTO = new BranchDTO(
                branch.getBranch_id(),
                branch.getBranch_name(),
                branch.getBranch_location(),
                branch.getBranch_contact_number()
        );
        customerDTO.setBranchDTO(branchDTO);
        return customerDTO;
    }

}
