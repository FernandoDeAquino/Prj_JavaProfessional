package com.dvfernandoaquino.dscommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dvfernandoaquino.dscommerce.dto.ProductDTO;
import com.dvfernandoaquino.dscommerce.entities.Product;
import com.dvfernandoaquino.dscommerce.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		/*Optional<Product> result = repository.findById(id);*/
		Product product = repository.findById(id).get();
		/*ProductDTO dto = new ProductDTO(product);*/
		return new ProductDTO(product);
	}
	
}
