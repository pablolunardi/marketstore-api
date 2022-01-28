package com.lunardi.marketstore.domain.exception;

import javax.persistence.EntityNotFoundException;

public class AddressNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	
	private static final String ADDRESS_NOT_FOUND = "There is no address with id %s and customer with id %s.";
	
	public AddressNotFoundException(Long addressId, Long customerId) {
		super(String.format(ADDRESS_NOT_FOUND, addressId, customerId));
	}

}
