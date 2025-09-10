package com.cpLab8sec2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cpLab8sec2.dto.CustomerRequest;
import com.cpLab8sec2.dto.CustomerResponse;
import com.cpLab8sec2.entity.Customer;
import com.cpLab8sec2.repository.CustomerRepository;

public class CustomerService {
	@Autowired
	private CustomerRepository repoCust;
	
	private CustomerResponse mapToResponse(Customer cust) {
		return new CustomerResponse(cust.getId(), cust.getName(), cust.getEmail());
	}
	
	private Customer toEntity(CustomerRequest request) {
		Customer customer = new Customer();
		customer.setName(request.getName());
		customer.setEmail(request.getEmail());
		return customer;
	}
	
	public List<CustomerResponse> getCustomerList(){
		List<Customer> customers = (List<Customer>) repoCust.findAll();
		return customers.stream()
				.map(c->new CustomerResponse(c.getId(),c.getName(), c.getEmail()))
				.toList();
	}
	
	public CustomerResponse getCustomerById(long id) {
		Customer customer = repoCust.findById(id).get();
		return mapToResponse(customer);
	}
	
	public CustomerResponse save(CustomerRequest cust) {
		Customer customer = toEntity(cust);
		repoCust.save(customer);
		return mapToResponse(customer);
	}
	
	public CustomerResponse addCustomer(CustomerRequest cust) {
		Customer customer = toEntity(cust);
		Customer custNew = repoCust.save(customer);
		return new CustomerResponse(
				custNew.getId(),
				custNew.getName(),
				custNew.getEmail());
	}
	
	public CustomerResponse updateCustomer(long id, CustomerRequest custNew) {
		Customer custExist = repoCust.findById(id).orElseThrow( () -> new CustomerNotFoundException(id));
		custExist.setName(custNew.getName());
		custExist.setEmail(custNew.getEmail());
		repoCust.save(custExist);
		return new CustomerResponse(custExist.getId(),
				custExist.getName(), custExist.getEmail());
	}
	
	public void deleteCustomerById(long id ) {
		Customer cust = repoCust.findById(id).orElseThrow( () -> new CustomerNotFoundException(id));
		repoCust.delete(cust);
	}
}
 