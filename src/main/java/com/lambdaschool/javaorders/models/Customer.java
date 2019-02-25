package com.lambdaschool.javaorders.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customers")
@Data
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "custcode")
    private Long custCode;

    @Column(nullable = false, name = "custname")
    private String custName;

    @Column(name = "custcity")
    private String custCity;

    @Column(name = "workingarea")
    private String workingArea;

    @Column(name = "custcountry")
    private String custCountry;

    private String grade;

    @Column(name = "openingamt")
    private double openingAmt;

    @Column(name = "receiveamt")
    private double receiveAmt;

    @Column(name = "paymentamt")
    private double paymentAmt;

    @Column(name = "outstandingamt")
    private double outstandingAmt;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "agentcode", nullable = false)
    @JsonIgnore
    private Agent agent;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "customer")
    @JsonIgnore
    private Set<Order> orders;

    public Customer()
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

}
