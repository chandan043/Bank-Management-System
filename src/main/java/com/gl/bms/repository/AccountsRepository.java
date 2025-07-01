package com.gl.bms.repository;


import com.gl.bms.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

// Make this into a Accounts repository
public interface AccountsRepository extends JpaRepository<Accounts,Long> {

    Accounts findByAccountNumber(Long accountNumber);
}
