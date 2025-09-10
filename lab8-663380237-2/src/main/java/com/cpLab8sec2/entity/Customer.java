package com.cpLab8sec2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column(name="name")
	private String name;
	@Column(name="email")
	private String email;
	public Customer() {
		
	}
	
	public Customer(String name,String email) {
		this.name = name;
		this.email = email;
	}
	
//	public Customer(Long id, String name) {
//		this.id = id;
//		this.name = name;
//	}
	
	public long getId() {return this.id;}

	public String getName() {return this.name;}

	public String getEmail() {return this.email;}
	
	public void setId(Long id) {this.id = id;}

	public void setName(String name) {this.name = name;}
	
	public void setEmail(String email) { this.email = email;}
	
	public String toString() {return id+" : "+name;}
}