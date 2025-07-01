package com.gl.bms.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/*
    Task is to add the required annotation to make this class a JPA entity
 */
@Entity
@Table(name = "Branchs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Branchs {

    /*
   task is to add a new column branch_id of type Integer.
   It should support the auto-generation of the primary key.
   Use appropriate annotation.
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer branch_id;


    /*
    task is to add a new column branch_name of type String.
    It should not be null.
    Use appropriate annotation.
    */
    @Column(name = "branch_name",nullable = false)
    private String branch_name;

    /*
    task is to add a new column branch_location of type String.
    It should not be null.
    Use appropriate annotation.
  */
    @Column(name = "branch_location",nullable = false)
    private String branch_location;

    /*
    task is to add a new column branch_contact_number of type Long.
    It should not be null.
    It should be unique.
    Use appropriate annotation.
 */
    @Column(name = "branch_contact_number",nullable = false,unique = true)
    private Long branch_contact_number;

    @OneToMany(cascade = CascadeType.ALL , orphanRemoval = true)
    @JoinColumn(name = "branch_id")
    private List<Accounts> accountsList = new ArrayList<>();
}
