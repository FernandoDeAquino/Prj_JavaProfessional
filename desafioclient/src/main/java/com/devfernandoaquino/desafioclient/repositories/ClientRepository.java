package com.devfernandoaquino.desafioclient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devfernandoaquino.desafioclient.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
