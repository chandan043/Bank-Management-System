package com.gl.bms.entity;

/*
    Task is to add the required annotation to make this class a JPA entity
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customers {

    /*
    task is to add a new column customer_id of type Integer.
    It should support the auto-generation of the primary key.
    Use appropriate annotation.
 */@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customer_id;


    /*
        task is to add a new column name of type String.
        It should not be null.
        Use appropriate annotation.
     */
    @Column(name = "name",nullable = false)
    private String name;

    /*
        task is to add a new column email of type String.
        It should not be null.
        It should be unique.
        Use appropriate annotation.
     */
    @Column(name = "email",nullable = false,unique = true)
    private String email;


    /*
      task is to add a new column phone_number of type String.
      It should not be null.
      It should be unique.
      Use appropriate annotation.
   */
    @Column(name = "phone_number",nullable = false,unique = true)
    private String contactNumber;
    ;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Accounts account;


}
