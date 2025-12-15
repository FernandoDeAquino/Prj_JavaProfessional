package com.dvfernandoaquino.dscommerce.dto;

import com.dvfernandoaquino.dscommerce.entities.Category;

public class CategoryDTO {
	
	private Long Id;
	private String name;
	
	public CategoryDTO() {
		
	}

	public CategoryDTO(Long id, String name) {
		Id = id;
		this.name = name;
	}

	public CategoryDTO(Category entity) {
		Id = entity.getId();
		name = entity.getName();
	}

	public Long getId() {
		return Id;
	}

	public String getName() {
		return name;
	}

}
