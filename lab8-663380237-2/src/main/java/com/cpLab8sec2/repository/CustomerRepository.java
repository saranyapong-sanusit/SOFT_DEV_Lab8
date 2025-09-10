package com.cpLab8sec2.repository;

import org.springframework.data.repository.CrudRepository;

import com.cpLab8sec2.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer,Long>{

}
