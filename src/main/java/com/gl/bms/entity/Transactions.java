package com.gl.bms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
    Task is to add the required annotation to make this class a JPA entity
 */
@Entity
@Table(name = "Transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {

    /*
    task is to add a new column transaction_id of type Integer.
    It should support the auto-generation of the primary key.
    Use appropriate annotation.
  */@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transaction_id;


    /*
    task is to add a new column transaction_type of type String.
    It should not be null.
    Use appropriate annotation.
  */
    @Column(name = "transaction_type",nullable = false)
    private String transaction_type;


    /*
    task is to add a new column account_balance of type Float.
    It should not be null.
    Use appropriate annotation.
*/
    @Column(name = "transaction_amount",nullable = false)
    private Float transaction_amount;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Accounts account;

}
