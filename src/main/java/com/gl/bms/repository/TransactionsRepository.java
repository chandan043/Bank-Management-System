package com.gl.bms.repository;


import com.gl.bms.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

// Make this into a Transactions repository
public interface TransactionsRepository extends JpaRepository<Transactions,Long> {
}
