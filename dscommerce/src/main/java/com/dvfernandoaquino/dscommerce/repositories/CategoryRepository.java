package com.dvfernandoaquino.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dvfernandoaquino.dscommerce.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
