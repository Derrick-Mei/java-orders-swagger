package com.lambdaschool.javaorders.repository;

import com.lambdaschool.javaorders.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long>
{
    // `/customer/order/`
    @Query(value = "" +
            "SELECT c.custname, o.ordnum " +
            "FROM customers c " +
            "LEFT JOIN orders o ORDER BY c.custname",
            nativeQuery = true)
    public List<Object[]> findCustomersAndOrders();

    // doesn't work I need to troubleshoot this one
    // `/customer/name/{custname}/`
    @Query(value = "SELECT * FROM orders WHERE orders.custcode IN (SELECT customers.custcode FROM customers LEFT JOIN orders ON customers.custcode = orders.custcode WHERE customers.custname LIKE :custname)", nativeQuery = true)
    public List<Object[]> findByCustName(@Param("custname") String custname);

    // `/customer/order/{custcode}`
    @Query(value = "Select * from orders WHERE orders.custcode IN (SELECT customers.custcode FROM customers LEFT JOIN orders ON customers.custcode = orders.custcode WHERE orders.custcode = :custcode)", nativeQuery = true)
    List<Object[]> findCustOrdersByCustCode(@Param("custcode") long custcode);

//    public Customer findByCustcode(long custcode);

//    public void deleteCustomerById(long id);





    // simon
    @Query(value = "SELECT c.custname, o.ordnum FROM customers AS c INNER JOIN orders AS o WHERE c.custname Like :custname", nativeQuery = true)
    List<Object[]> findCustomerByNameWithOrders(@Param("custname") String custname);
}

