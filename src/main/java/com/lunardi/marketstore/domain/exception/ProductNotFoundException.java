package com.lunardi.marketstore.domain.exception;

import javax.persistence.EntityNotFoundException;

public class ProductNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	
	private static final String PRODUCT_NOT_FOUND = "There is not product with id %s.";
	
	public ProductNotFoundException(Long productId) {
		super(String.format(PRODUCT_NOT_FOUND, productId));
	}

}
