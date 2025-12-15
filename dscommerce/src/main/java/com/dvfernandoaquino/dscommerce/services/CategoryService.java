package com.dvfernandoaquino.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dvfernandoaquino.dscommerce.dto.CategoryDTO	;
import com.dvfernandoaquino.dscommerce.entities.Category;
import com.dvfernandoaquino.dscommerce.entities.Product;
import com.dvfernandoaquino.dscommerce.repositories.CategoryRepository;
import com.dvfernandoaquino.dscommerce.services.exceptions.DatabaseException;
import com.dvfernandoaquino.dscommerce.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository repository;
	
	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Category category = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new CategoryDTO(category); 
	}
	
	@Transactional(readOnly = true)
	public Page<CategoryDTO> findAll(Pageable pageable) {
		Page<Category> result = repository.findAll(pageable);
		return result.map(x -> new CategoryDTO(x));
		
	}
	
	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setName(dto.getName());
        Category category = repository.findByName(dto.getName());
        if(category != null) {
            throw new DataIntegrityViolationException("Categorria já cadastrada");
        }
		entity = repository.save(entity);
		return new CategoryDTO(entity);
	}	
	
	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		try {
			Category entity = repository.getReferenceById(id);
			entity.setName(dto.getName());
			entity = repository.save(entity);
			return new CategoryDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
	}

	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		try {
	        	repository.deleteById(id);    		
		}
	    	catch (DataIntegrityViolationException e) {
	        	throw new DatabaseException("Falha de integridade referencial");
	   	}
	}	  

}
