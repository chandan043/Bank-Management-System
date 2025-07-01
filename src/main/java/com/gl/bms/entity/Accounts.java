package com.gl.bms.entity;


/*
    Task is to add the required annotation to make this class a JPA entity
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Accounts {

    /*
    task is to add a new column account_id of type Integer.
    It should support the auto-generation of the primary key.
    Use appropriate annotation.
 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer account_id;

    /*
    task is to add a new column account_number of type Integer.
    It should not be null.
    It should be unique.
    Use appropriate annotation.
 */
    @Column(name = "account_number",nullable = false,unique = true)
    private Long accountNumber;


    /*
    task is to add a new column account_type (Savings or Current) of type String.
    It should not be null.
    Use appropriate annotation.
 */
    @Column(name = "account_type",nullable = false)
    private String account_type;


    /*
    task is to add a new column account_balance of type String.
    It should not be null.
    Use appropriate annotation.
 */
    @Column(name = "account_balance",nullable = false)
    private Float account_balance;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private Customers customer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "branch_id")
    private Branchs branch;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transactions> transactionsList = new ArrayList<>();

}
