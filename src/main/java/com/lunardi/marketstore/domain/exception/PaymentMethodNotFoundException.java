package com.lunardi.marketstore.domain.exception;

import javax.persistence.EntityNotFoundException;

public class PaymentMethodNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	
	private static final String PAYMENT_METHOD_NOT_FOUND = "There is no payment method with id %s.";
	
	public PaymentMethodNotFoundException(Long paymentMethodId) {
		super(String.format(PAYMENT_METHOD_NOT_FOUND, paymentMethodId));
	}

}
