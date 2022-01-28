package com.lunardi.marketstore.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lunardi.marketstore.api.dto.CustomerDTO;
import com.lunardi.marketstore.api.dto.input.CustomerInputDTO;
import com.lunardi.marketstore.domain.model.Customer;
import com.lunardi.marketstore.domain.service.CustomerService;


@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<CustomerDTO> findAll() {
		List<Customer> customers = customerService.findAll();
		
		return toCollectionlDTO(customers);
	}
	
	@GetMapping("/{customerId}")
	public CustomerDTO get(@PathVariable Long customerId) {
		return toDTO(customerService.getCustomer(customerId));
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public CustomerDTO create(@Valid @RequestBody CustomerInputDTO customerInputDTO) {
		Customer customer = customerService.save(toModel(customerInputDTO));
		
		return toDTO(customer);
	}
	
	@PutMapping("/{customerId}")
	public CustomerDTO update(@PathVariable Long customerId, @Valid @RequestBody CustomerInputDTO customerInputDTO) {
		Customer customer = customerService.getCustomer(customerId);
		
		modelMapper.map(customerInputDTO, customer);
		
		customer = customerService.save(customer);
				
		return toDTO(customer);
	}
	
	private List<CustomerDTO> toCollectionlDTO(List<Customer> customer) {
		return customer.stream().map(this::toDTO)
				.collect(Collectors.toList());
	}
	
	private CustomerDTO toDTO(Customer customer) {
		return modelMapper.map(customer, CustomerDTO.class);
	}
	
	private Customer toModel(CustomerInputDTO customerInputDTO) {
		return modelMapper.map(customerInputDTO, Customer.class);
	}
	
}
