package com.lambdaschool.javaorders.controllers;

import com.lambdaschool.javaorders.models.Agent;
import com.lambdaschool.javaorders.repository.AgentRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Orders Swagger Application - AgentController", description = "SQLite plus Swagger by DKM")
@RestController
@RequestMapping(path = {}, produces = MediaType.APPLICATION_JSON_VALUE)
public class AgentController
{
    @Autowired
    AgentRepository agentrepos;

    // /agents - return all the agents
    @GetMapping("/agents")
    public List<Agent> allAgentsOnly()
    {
        return agentrepos.findAll();
    }

    @GetMapping("/agents/agentcode/{agentcode}")
    public Agent findAgentById(@PathVariable long agentcode)
    {
        var agentById = agentrepos.findById(agentcode);
        return agentById.isPresent() ? agentById.get() : null;
    }


//agents - Returns all agents with their customers
    @GetMapping("/agents/customers")
    public List<Object[]> findAllAgents()
    {
        return agentrepos.findAgentsAndCustomers();
    }

//agents/orders - Return a list with the agents name and associated order number and order description
    @GetMapping("/agents/orders")
    public List<Object[]> findAgentsAndOrders()
    {
        return agentrepos.findAgentsAndOrders();
    }

    @PostMapping("/agents")
    public Agent addNewAgent(@RequestBody Agent newAgent)
    {
        return agentrepos.save(newAgent);
    }

    @PutMapping("/agents/agentcode/{agentcode}")
    public Agent updateAgent(@RequestBody Agent updatedAgent, @PathVariable long agentcode)
    {
        var agentById = agentrepos.findById(agentcode);
        if (agentById.isPresent())
        {
            updatedAgent.setId(agentcode);
            agentrepos.save(updatedAgent);
            return updatedAgent;
        }
        else
        {
            return null;
        }
    }

//agents/{agentcode} - Deletes an agent if they are not assigned to a customer or order (Stretch Goal)

}


