package com.lunardi.marketstore.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lunardi.marketstore.api.dto.CustomerAddressDTO;
import com.lunardi.marketstore.api.dto.input.CustomerAddressInputDTO;
import com.lunardi.marketstore.domain.model.CustomerAddress;
import com.lunardi.marketstore.domain.service.CustomerAddressService;


@RestController
@RequestMapping("/customers/{customerId}/addresses")
public class CustomerAddressController {
	
	@Autowired
	private CustomerAddressService customerAddressService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<CustomerAddressDTO> findAll(@PathVariable Long customerId) {
		List<CustomerAddress> customerAddress = customerAddressService.findByCustomer(customerId);
		
		return toCollectionlDTO(customerAddress);
	}
	
	@GetMapping("/{customerAddressId}")
	public CustomerAddressDTO get(@PathVariable Long customerId, @PathVariable Long customerAddressId) {
		return toDTO(customerAddressService.getCustomerAddress(customerId, customerAddressId));
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public CustomerAddressDTO create(@PathVariable Long customerId, @Valid @RequestBody CustomerAddressInputDTO customerAddressInputDTO) {
		CustomerAddress customerAddress = customerAddressService.save(customerId, toModel(customerAddressInputDTO));
		
		return toDTO(customerAddress);
	}
	
	@PutMapping("/{customerAddressId}")
	public CustomerAddressDTO update(@PathVariable Long customerId, @PathVariable Long customerAddressId, @Valid @RequestBody CustomerAddressInputDTO customerAddressInputDTO) {
		CustomerAddress customerAddress = customerAddressService.getCustomerAddress(customerId, customerAddressId);
		
		modelMapper.map(customerAddressInputDTO, customerAddress);
		
		customerAddress = customerAddressService.save(customerId, customerAddress);
				
		return toDTO(customerAddress);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{customerAddressId}")
	public void delete(@PathVariable Long customerId, @PathVariable Long customerAddressId) {
		CustomerAddress customerAddress = customerAddressService.getCustomerAddress(customerId, customerAddressId);
		
		customerAddressService.delete(customerAddress.getId(), customerId);
	}
	
	private List<CustomerAddressDTO> toCollectionlDTO(List<CustomerAddress> customerAddresses) {
		return customerAddresses.stream().map(this::toDTO)
				.collect(Collectors.toList());
	}
	
	private CustomerAddressDTO toDTO(CustomerAddress customerAddress) {
		return modelMapper.map(customerAddress, CustomerAddressDTO.class);
	}
	
	private CustomerAddress toModel(CustomerAddressInputDTO customerAddressInputDTO) {
		return modelMapper.map(customerAddressInputDTO, CustomerAddress.class);
	}
	
}
