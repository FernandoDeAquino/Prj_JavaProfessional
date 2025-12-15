package com.dvfernandoaquino.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dvfernandoaquino.dscommerce.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
