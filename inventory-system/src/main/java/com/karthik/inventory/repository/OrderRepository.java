package com.karthik.inventory.repository;

import com.karthik.inventory.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, String>{
    List<Order> findOneByOrderId(String id);

    List<Order> findAll();

}
