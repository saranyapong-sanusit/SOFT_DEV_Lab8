package com.cpLab8sec2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CustomerRequest {
	@NotBlank(message="customerName must not be blank")
	@JsonProperty("customerName")
	private String name;
	
	@NotBlank(message="Email must not be blank")
	@Email(message="Email must be valid")
	private String email;
	public String getName() {
		return this.name;
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setName(String name) {
		this.name=name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}	
}
