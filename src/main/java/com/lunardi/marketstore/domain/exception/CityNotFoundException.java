package com.lunardi.marketstore.domain.exception;

import javax.persistence.EntityNotFoundException;

public class CityNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	
	private static final String CITY_NOT_FOUND = "There is no city with id %s.";
	
	public CityNotFoundException(Long cityId) {
		super(String.format(CITY_NOT_FOUND, cityId));
	}

}
