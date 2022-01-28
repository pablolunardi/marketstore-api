package com.lunardi.marketstore.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lunardi.marketstore.domain.exception.AddressNotFoundException;
import com.lunardi.marketstore.domain.model.Customer;
import com.lunardi.marketstore.domain.model.CustomerAddress;
import com.lunardi.marketstore.domain.repository.CustomerAddressRepository;

@Service
public class CustomerAddressService {

	@Autowired
	private CustomerAddressRepository customerAddressRepository;
	
	@Autowired
	private CustomerService customerService;
	

	public CustomerAddress findById(Long addressId, Long customerId) {
		return customerAddressRepository.findById(addressId)
				.orElseThrow(() -> new AddressNotFoundException(addressId, customerId));
	}
	
	public List<CustomerAddress> findByCustomer(Long customerId) {
		return customerAddressRepository.findByCustomerId(customerId);
	}

	public CustomerAddress getCustomerAddress(Long customerId, Long customerAddressId) {
		return customerAddressRepository.findByIdAndCustomerId(customerAddressId, customerId)
				.orElseThrow(() -> new AddressNotFoundException(customerAddressId, customerId));
	}
	
	@Transactional
	public CustomerAddress save(Long costumerId, CustomerAddress customerAddress) {
		Customer customer = customerService.getCustomer(costumerId);
		
		customerAddress.setCustomer(customer);
		
		return customerAddressRepository.save(customerAddress);
	}
	
	@Transactional
	public void delete(Long addressId, Long customerId) {
		try {
			customerAddressRepository.deleteById(addressId);
			customerAddressRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new AddressNotFoundException(addressId, customerId);
		}
	}
	
	
}
