package com.lunardi.marketstore.domain.exception;

import javax.persistence.EntityNotFoundException;

public class CustomerNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	
	private static final String CUSTOMER_NOT_FOUND = "There is no customer with id %s.";
	
	public CustomerNotFoundException(Long customerId) {
		super(String.format(CUSTOMER_NOT_FOUND, customerId));
	}

}
