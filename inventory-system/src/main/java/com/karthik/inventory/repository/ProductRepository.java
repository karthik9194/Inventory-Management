package com.karthik.inventory.repository;

import com.karthik.inventory.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    List<Product> findOneByProductid(int id);

    List<Product> findOneByUpcCode(String upcCode);

    List<Product> findAll();
}
