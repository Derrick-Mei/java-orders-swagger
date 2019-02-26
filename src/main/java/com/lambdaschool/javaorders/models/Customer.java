package com.lambdaschool.javaorders.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "customers")
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "custcode")
    private long custcode;

    @Column(nullable = false, name = "custname")
    private String custname;

    @Column(name = "custcity")
    private String custcity;

    @Column(name = "workingarea")
    private String workingarea;

    @Column(name = "custcountry")
    private String custcountry;

    private String grade;

    @Column(name = "openingamt")
    private double openingamt;

    @Column(name = "receiveamt")
    private double receiveamt;

    @Column(name = "paymentamt")
    private double paymentamt;

    @Column(name = "outstandingamt")
    private double outstandingamt;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "agentcode", nullable = false)
//    @JsonIgnore
    private Agent agent;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "customer")
    @JsonIgnore
    private Set<Order> orders;

    public Customer()
    {
    }

}
