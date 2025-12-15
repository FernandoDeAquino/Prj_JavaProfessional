package com.dvfernandoaquino.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dvfernandoaquino.dscommerce.dto.UserDTO;
import com.dvfernandoaquino.dscommerce.entities.User;
import com.dvfernandoaquino.dscommerce.repositories.UserRepository;
import com.dvfernandoaquino.dscommerce.services.exceptions.DatabaseException;
import com.dvfernandoaquino.dscommerce.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		User user = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new UserDTO(user); 
	}
	
	@Transactional(readOnly = true)
	public Page<UserDTO> findAll(Pageable pageable) {
		Page<User> result = repository.findAll(pageable);
		return result.map(x -> new UserDTO(x));
	}
	
	
	@Transactional
	public UserDTO insert(UserDTO dto) {
		User entity = new User();
		copyDtoEntity(dto,entity);
		User user = repository.findByEmail(dto.getEmail());
		if(user != null) {
			throw new DataIntegrityViolationException("Email já cadastrado");
		}
		
		entity = repository.save(entity);
		return new UserDTO(entity);
	}
	
	
	@Transactional
	public UserDTO update(Long id, UserDTO dto) {
		try {
			User entity = repository.getReferenceById(id);
			copyDtoEntity(dto, entity);
 			entity = repository.save(entity);
			return new UserDTO(entity);			
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado.");
		}

	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException("Recurso não encontrado.");
		}
		try {
			repository.deleteById(id);
		} 
			catch (DataIntegrityViolationException e) {
				throw new DatabaseException("Falha de integridade referencial");	
		}
	}
	
	private void copyDtoEntity(UserDTO dto, User entity) {
		entity.setName(dto.getName());
		entity.setPhone(dto.getPhone());
		entity.setBirthDate(dto.getBirthDate());
		entity.setPassword(dto.getPassword());
		entity.setEmail(dto.getEmail());	
	}
	

}
