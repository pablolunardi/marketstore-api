package com.lunardi.marketstore.domain.exception;

import javax.persistence.EntityNotFoundException;

public class MerchantOwnerNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	
	private static final String MERCHANT_OWNER_NOT_FOUND = "There is no merchant owner with id %s.";
	
	public MerchantOwnerNotFoundException(Long merchantOwnerId) {
		super(String.format(MERCHANT_OWNER_NOT_FOUND, merchantOwnerId));
	}

}
