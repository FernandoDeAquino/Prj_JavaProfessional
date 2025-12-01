package com.dvfernandoaquino.dscommerce.dto;

import com.dvfernandoaquino.dscommerce.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDTO {

	private Long Id;
	
	@Size(min= 3, max = 80, message="Nome precisa ter de 3 a 80 caracteres")
	@NotBlank(message ="campo requerido")
	private String name;
	
	@Size(min= 10, message="Descrição precisa ter de 10 caracteres")
	@NotBlank(message ="campo requerido")
	private String description;
	
	@Positive(message="o preço deve ser positivo")
	private Double price;
	private String imgUrl;
	
	public ProductDTO() {
		
	}
	
	public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
		Id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}
	
	public ProductDTO(Product entity) {
		Id = entity.getId();
		name = entity.getName();
		description = entity.getDescription();
		price = entity.getPrice();
		imgUrl = entity.getImgUrl();
	}

	public Long getId() {
		return Id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}

	public String getImgUrl() {
		return imgUrl;
	}
	
	
	@Override
	public String toString() {
		return "ProductDTO [Id=" + Id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", imgUrl=" + imgUrl + "]";
	}

	
}