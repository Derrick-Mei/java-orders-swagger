package com.lambdaschool.javaorders.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "agents")
@Data //@Data from lombok allows me to not have to do boilerplate for getters and setters
public class Agent
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //this means it's a primary key
    @Column(name = "agentcode")
    private long id;

    @Column(name = "agentname")
    private String agentName;

    @Column(name = "workingarea")
    private String workingArea;

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

    public Set<Order> getOrders()
    {
        return orders;
    }

    public void setOrders(Set<Order> orders)
    {
        this.orders = orders;
    }

    public Set<Customer> getCustomers()
    {
        return customers;
    }

    public void setCustomers(Set<Customer> customers)
    {
        this.customers = customers;
    }
}
