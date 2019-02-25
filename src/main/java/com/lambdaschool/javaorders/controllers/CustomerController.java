package com.lambdaschool.javaorders.controllers;

import com.lambdaschool.javaorders.models.Customer;
import com.lambdaschool.javaorders.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = {}, produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController
{
    @Autowired
    CustomerRepository customerrepos;

    // /customers - returns all the customer
    @GetMapping("/customers")
    public List<Customer> allCustomers()
    {
        List<Customer> allCustomers =  customerrepos.findAll();
        return allCustomers != null ? allCustomers : null;
    }

    // /customers/custcode/{custcode}
    @GetMapping("/customers/custcode/{custcode}")
    public Customer findCustById(@PathVariable long custcode)
    {
        var foundCust = customerrepos.findById(custcode);
        return foundCust.isPresent() ? foundCust.get() : null;

//        if (foundCust.isPresent())
//        {
//            return foundCust.get();
//        }
//        else
//        {
//            return null;
//        }

    }

//customer/order - Returns all customers with their orders
    @GetMapping("/customers/order")
    public List<Object[]> findCustomersAndOrders()
    {
        // will return null if there is no data with that query
        return customerrepos.findCustomersAndOrders();
    }

    // POST /customers - adds a customer
    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer newCustomer) throws URISyntaxException
    {
        // need to test if it returns the new customer or I need to write more code
        return customerrepos.save(newCustomer);
    }


    //PUT /customers/custocode/{custcode} - updates a customer based on custcode
    @PutMapping("customers/custcode/{custcode}")
    public Customer updateCustomer(@RequestBody Customer updatedCustomer, @PathVariable long custcode) throws URISyntaxException
    {
        Optional<Customer> custToUpdate = customerrepos.findById(custcode);
        if (custToUpdate.isPresent())
        {
            updatedCustomer.setCustCode(custcode);
            customerrepos.save(updatedCustomer);
            return updatedCustomer;
        }
        else
        {
            return null;
        }
    }

    // DOESN'T WORK YET!!! Query is wrong
    //customer/name/{custname} - Returns all orders for a particular based on name
    @GetMapping("/customers/name/{custname}")
    public List<Object[]> findCustomerOrdersByName(@PathVariable String custname)
    {
        return customerrepos.findByCustName(custname);
    }

    //customer/order/{custcode} - Returns all orders for a particular customer based on custcode
    @GetMapping("/customers/order/{custcode}")
    public List<Object[]> findCustOrdersByCustCode(@PathVariable long custcode)
    {
        return customerrepos.findCustOrdersByCustCode(custcode);
    }

    // Does not work completely!!!  It will only delete customers with no orders.
    //customer/{custcode} - Deletes a customer based off of their custcode and deletes all their associated orders
    // DELETE /customers/custcode/{custcode} - Deletes a customer based off of their custcode and deletes all their associated orders
    @DeleteMapping("/customers/custcode/{custcode}")
    public Optional<Customer> deleteByCustCode(@PathVariable long custcode)
    {
        Optional<Customer> custToBeDeleted = customerrepos.findById(custcode);
        if (custToBeDeleted.isPresent())
        {
            customerrepos.deleteById(custcode);
            return custToBeDeleted;
        }
        else
        {
            return null;
        }
    }





    // simon
    @GetMapping("/customers/name2/{custname}")
    public List<Object[]> getCustomerByNameWithAllOrders(@PathVariable String custname) {
        return customerrepos.findCustomerByNameWithOrders(custname);
    }
}

