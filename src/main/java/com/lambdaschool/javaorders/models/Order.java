package com.lambdaschool.javaorders.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordnum")
    private long id;

    @Column(name = "ordamount")
    private double ordamount;

    @Column(name = "advanceamount")
    private double advanceamount;

    @ManyToOne
    @JoinColumn(name = "custcode", nullable = false)
//    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "agentcode", nullable = false)
//    @JsonIgnore
    private Agent agent;

    @Column(name = "orddescription")
    private String orddescription;

    public Order()
    {
    }

}
