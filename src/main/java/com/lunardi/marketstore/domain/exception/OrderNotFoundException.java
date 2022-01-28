package com.lunardi.marketstore.domain.exception;

import javax.persistence.EntityNotFoundException;

public class OrderNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	
	private static final String ORDER_NOT_FOUND = "There is no order with id %s.";
	
	public OrderNotFoundException(Long orderId) {
		super(String.format(ORDER_NOT_FOUND, orderId));
	}

}
