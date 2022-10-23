package com.greatLearning.customerRelationshipManagement.service;

import java.util.List;

import com.greatLearning.customerRelationshipManagement.entity.Customer;

public interface CustomerService {

	List<Customer> findAll();

	Customer findById(int id);

	void save(Customer theCustomer);

	void deleteById(int id);

}
