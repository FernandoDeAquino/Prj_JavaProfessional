package com.devfernandoaquino.desafioclient.services;

import java.nio.ReadOnlyBufferException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devfernandoaquino.desafioclient.dto.ClientDTO;
import com.devfernandoaquino.desafioclient.entities.Client;
import com.devfernandoaquino.desafioclient.exception.DataIntegrityViolationException;
import com.devfernandoaquino.desafioclient.exception.ResourceNotFoundException;
import com.devfernandoaquino.desafioclient.repositories.ClientRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {
	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Client client = clientRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new ClientDTO(client);
	}
	
	@Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> result = clientRepository.findAll(pageable);
        return result.map(x -> new ClientDTO(x));
    }
	
	@Transactional
    public ClientDTO insert(ClientDTO dto) {
		Client client =  new Client();
		copyDtoToEntity(dto, client);
		if(client.getName().isEmpty()) {
			/////throw new DataIntegrityViolationException("Nome: não pode ser vazio - CLIENTSERVICE.");
		}

		client = clientRepository.save(client);
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
    	try {
	        Client entity = clientRepository.getReferenceById(id);
	        copyDtoToEntity(dto, entity);
	        entity = clientRepository.save(entity);
	        return new ClientDTO(entity);
    	}
    	catch (EntityNotFoundException e) {
    		throw new ResourceNotFoundException("Recurso não encontrado.");	
    	}
        
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
    	if(!clientRepository.existsById(id)) {
    		throw new ResourceNotFoundException("Recurso não encontrado");
    	}
    	clientRepository.deleteById(id);
    }
    
    private void copyDtoToEntity(ClientDTO dto, Client entity) {
    	entity.setName(dto.getName());
    	entity.setCpf(dto.getCpf());
    	entity.setIncome(dto.getIncome());
    	entity.setBirthDate(dto.getBirthDate());
    	entity.setChildren(dto.getChildren());
    }
	
	
}
