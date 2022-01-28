package com.lunardi.marketstore.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lunardi.marketstore.domain.exception.CustomerNotFoundException;
import com.lunardi.marketstore.domain.model.Customer;
import com.lunardi.marketstore.domain.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}
	
	public Customer findById(Long customerId) {
		return customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException(customerId));
	}

	public Customer getCustomer(Long customerId) {
		return customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException(customerId));
	}
	
	@Transactional
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}
	
}
