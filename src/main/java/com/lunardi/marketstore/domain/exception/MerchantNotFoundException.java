package com.lunardi.marketstore.domain.exception;

import javax.persistence.EntityNotFoundException;

public class MerchantNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	
	private static final String MERCHANT_NOT_FOUND = "There is no merchant with id %s.";
	
	public MerchantNotFoundException(Long merchantId) {
		super(String.format(MERCHANT_NOT_FOUND, merchantId));
	}

}
