package com.dvfernandoaquino.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dvfernandoaquino.dscommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	

}
