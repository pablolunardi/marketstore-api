package com.lunardi.marketstore.domain.exception;

import javax.persistence.EntityNotFoundException;

public class StateNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	
	private static final String STATE_NOT_FOUND = "There is no state with id %s.";
	
	public StateNotFoundException(Long stateId) {
		super(String.format(STATE_NOT_FOUND, stateId));
	}

}
