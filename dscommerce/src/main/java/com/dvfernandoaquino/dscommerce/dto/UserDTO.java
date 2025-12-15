package com.dvfernandoaquino.dscommerce.dto;

import java.time.LocalDate;

import com.dvfernandoaquino.dscommerce.entities.User;

import jakarta.persistence.Column;

public class UserDTO {
	private Long Id;
	private String name;
	@Column(unique = true)
	private String email;
	private String phone;
	private LocalDate birthDate;
	private String password;
	
	public UserDTO() {
		
	}

	public UserDTO(Long id, String name, String email, String phone, LocalDate birthDate, String password) {
		Id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.birthDate = birthDate;
		this.password = password;
	}

	public UserDTO(User entity) {
		Id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
		this.phone = entity.getPhone();
		this.birthDate = entity.getBirthDate();
		this.password = entity.getPassword();
	}
	public Long getId() {
		return Id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "UserDTO [Id=" + Id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", birthDate="
				+ birthDate + ", password=" + password + "]";
	}

}
