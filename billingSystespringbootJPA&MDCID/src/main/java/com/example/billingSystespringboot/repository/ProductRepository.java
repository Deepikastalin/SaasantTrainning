package com.example.billingSystespringboot.repository;

import com.example.billingSystespringboot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
