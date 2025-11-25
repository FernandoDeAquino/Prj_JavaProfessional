package com.dvfernandoaquino.dscommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dvfernandoaquino.dscommerce.dto.ProductDTO;
import com.dvfernandoaquino.dscommerce.entities.Product;
import com.dvfernandoaquino.dscommerce.repositories.ProductRepository;

import jakarta.persistence.Entity;

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
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(Pageable pageable) {
		Page<Product> result = repository.findAll(pageable);
		return result.map(x -> new ProductDTO(x));
		
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		copyDtoEntity(dto,entity);
		entity = repository.save(entity);
		return new ProductDTO(entity);
	}
	
	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		Product entity = repository.getReferenceById(id);
		copyDtoEntity(dto,entity);
		entity = repository.save(entity);
		return new ProductDTO(entity);
	}

	private void copyDtoEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());
	}
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
}
