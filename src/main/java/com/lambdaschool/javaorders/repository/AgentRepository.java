package com.lambdaschool.javaorders.repository;

import com.lambdaschool.javaorders.models.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AgentRepository extends JpaRepository<Agent, Long>
{
    @Query(value = "SELECT ('Agent Name:    ' || a.agentname), ('Customer Name: ' || c.custname) FROM agents a, customers c WHERE a.agentcode = c.agentcode ORDER BY a.agentname", nativeQuery = true)
    List<Object[]> findAgentsAndCustomers();

    @Query(value = "SELECT ('Agent Name: ' || a.agentname), ('OrderNum:   ' || o.ordnum), ('OrdDesc:    ' || o.orddescription) FROM agents a LEFT JOIN orders o ORDER BY a.agentname", nativeQuery = true)
    List<Object[]> findAgentsAndOrders();

}