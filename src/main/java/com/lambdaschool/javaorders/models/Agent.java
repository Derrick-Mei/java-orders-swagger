package com.lambdaschool.javaorders.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "agents")
@Data
public class Agent
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //this means it's a primary key
    @Column(name = "agentcode")
    private long id;

    @Column(name = "agentname")
    private String agentname;

    @Column(name = "workingarea")
    private String workingarea;

    private double commission;

    private String phone;

    private String country;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "agent")
    @JsonIgnore
    private Set<Order> orders;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "agent")
    @JsonIgnore
    private Set<Customer> customers;

    // default constructor for JPA
    public Agent()
    {
    }


}
