package com.cpLab8sec2.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpLab8sec2.dto.CustomerRequest;
import com.cpLab8sec2.dto.CustomerResponse;
import com.cpLab8sec2.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

	@Autowired
	private CustomerService custService;

	@GetMapping
	public ResponseEntity<List<CustomerResponse>> getAllCustomer(){ //select * from Customer
		List<CustomerResponse> customer = custService.getCustomerList();
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	
	@GetMapping("/{id}") //select * from Customer where customer.id = id
	public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
		CustomerResponse customer = custService.getCustomerById(id);
	    return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> addCustomer( @RequestBody @Valid CustomerRequest cust,BindingResult br){
		if(br.hasErrors()) {
			Map<String,Object> err = new LinkedHashMap<>();
			err.put("status", 400);
			err.put("error", "Bad Request");
			err.put("message", "Validation failed");
			err.put("fieldError", br.getFieldErrors().stream()
				.map(fe->Map.of(
						"field", fe.getField(),
						"message", fe.getDefaultMessage(),
						"rejectedValue", fe.getRejectedValue()
						)).toList());
			
			return ResponseEntity.badRequest().body(err);
			
		}
		else {
			CustomerResponse response = custService.save(cust);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable Long id,
			@RequestBody @Valid CustomerRequest custReq){
		return new ResponseEntity<>(custService.updateCustomer(id, custReq), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCustomerById(@PathVariable Long id){
		CustomerResponse custDel = custService.getCustomerById(id);
		if(custDel != null) {
			custService.deleteCustomerById(id);
			return new ResponseEntity<>("deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Customer does not exist",HttpStatus.NOT_FOUND);
	}	
}