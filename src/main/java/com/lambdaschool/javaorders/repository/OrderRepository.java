package com.lambdaschool.javaorders.repository;

import com.lambdaschool.javaorders.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>
{
}
