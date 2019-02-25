package com.lambdaschool.javaorders.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Data
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordnum")
    private long id;

    @Column(name = "ordamount")
    private double ordAmount;

    @Column(name = "advanceamount")
    private double advanceAmount;

    @ManyToOne
    @JoinColumn(name = "custcode", nullable = false)
//    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "agentcode", nullable = false)
//    @JsonIgnore
    private Agent agent;

    @Column(name = "orddescription")
    private String ordDescription;

    public Order()
    {
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public Agent getAgent()
    {
        return agent;
    }

    public void setAgent(Agent agent)
    {
        this.agent = agent;
    }
}
