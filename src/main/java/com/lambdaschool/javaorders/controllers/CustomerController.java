package com.lambdaschool.javaorders.controllers;

import com.lambdaschool.javaorders.models.Customer;
import com.lambdaschool.javaorders.repository.CustomerRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Api(value = "Orders Swagger Application - CustomerController", description = "SQLite plus Swagger by DKM")
@RestController
@RequestMapping(path = {}, produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController
{
    @Autowired
    CustomerRepository customerrepos;

    // /customers - returns all the customer
    @ApiOperation(value = "list All Customers", response = List.class)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "DKM Successfully received list"),
                    @ApiResponse(code = 401, message = "DKM You are not authorized to the view the resource"),
                    @ApiResponse(code = 403, message = "DKM Accessing the resource you were trying to reach is forbidden"),
                    @ApiResponse(code = 404, message = "DKM The resource you were trying to reach is not found")
            })
    @GetMapping("/customers")
    public List<Customer> allCustomers()
    {
        List<Customer> allCustomers =  customerrepos.findAll();
        return allCustomers != null ? allCustomers : null;
    }

    // /customers/custcode/{custcode}
    @ApiOperation(value = "Customer based off of customer id", response = Customer.class)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "Successfully received customer - DKM"),
                    @ApiResponse(code = 401, message = "You are not authorized to the view the resource - DKM"),
                    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden - DKM"),
                    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found - DKM")
            })
    @GetMapping("/customers/custcode/{custcode}")
    public Customer findCustById(@PathVariable long custcode)
    {
        var foundCust = customerrepos.findById(custcode);
        return foundCust.isPresent() ? foundCust.get() : null;
    }

    //customer/order - Returns all customers with their orders
    @GetMapping("/customers/order")
    public List<Object[]> findCustomersAndOrders()
    {
        return customerrepos.findCustomersAndOrders();
    }

    // POST /customers - adds a customer
    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer newCustomer) throws URISyntaxException
    {
        return customerrepos.save(newCustomer);
    }


    //PUT /customers/custocode/{custcode} - updates a customer based on custcode
    @PutMapping("customers/custcode/{custcode}")
    public Customer updateCustomer(@RequestBody Customer updatedCustomer, @PathVariable long custcode) throws URISyntaxException
    {
        Optional<Customer> custToUpdate = customerrepos.findById(custcode);
        if (custToUpdate.isPresent())
        {
            updatedCustomer.setCustcode(custcode);
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

}

