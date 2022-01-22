package com.lunardi.marketstore.domain.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class EntityInUseException extends DataIntegrityViolationException {

	private static final long serialVersionUID = 1L;

	
	public EntityInUseException(String msg) {
		super(msg);
	}

}
