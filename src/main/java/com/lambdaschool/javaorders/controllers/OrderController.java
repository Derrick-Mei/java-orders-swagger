package com.lambdaschool.javaorders.controllers;

import com.lambdaschool.javaorders.models.Order;
import com.lambdaschool.javaorders.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = {"/orders"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController
{

    @Autowired
    OrderRepository orderrepos;

    // /orders - return all the orders
    @GetMapping("")
    public List<Order> allOrders()
    {
        return orderrepos.findAll();
    }

    @GetMapping("/ordnum/{ordnum}")
    public Order findOrderById(@PathVariable long ordnum)
    {
        var foundOrder = orderrepos.findById(ordnum);

        return foundOrder.isPresent() ? foundOrder.get() : null;
    }

    @PostMapping("")
    public Order addNewOrder(@RequestBody Order newOrder) throws URISyntaxException
    {
        return orderrepos.save(newOrder);
    }

    @PutMapping("/ordnum/{ordnum}")
    public Order updateOrder(@RequestBody Order updatedOrder, @PathVariable long ordnum)
    {
        Optional<Order> orderById = orderrepos.findById(ordnum);
        if (orderById.isPresent())
        {
            updatedOrder.setId(ordnum);
            orderrepos.save(updatedOrder);
            return updatedOrder;
        }
        else
        {
            return null;
        }
    }

}
