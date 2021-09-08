package com.karthik.inventory.repository;

import com.karthik.inventory.model.entity.Cashier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CashierRepository extends JpaRepository<Cashier, Integer>{
    List<Cashier> findOneByCashierId(int id);

    List<Cashier> findAll();
}
