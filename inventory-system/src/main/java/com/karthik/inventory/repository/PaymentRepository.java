package com.karthik.inventory.repository;

import com.karthik.inventory.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
    List<Payment> findOneByid(Long id);

    List<Payment> findAll();
}
