package com.lunardi.marketstore.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lunardi.marketstore.domain.exception.CityNotFoundException;
import com.lunardi.marketstore.domain.exception.EntityInUseException;
import com.lunardi.marketstore.domain.model.City;
import com.lunardi.marketstore.domain.model.State;
import com.lunardi.marketstore.domain.repository.CityRepository;

@Service
public class CityService {

	private static final String CITY_IN_USE_MSG = "The city with id %s can't be deleted because it's in use";
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private StateService stateService;
	
	public List<City> findAll() {
		return cityRepository.findAll();
	}

	public City getCity(Long cityId) {
		return cityRepository.findById(cityId)
				.orElseThrow(() -> new CityNotFoundException(cityId));
	}
	
	@Transactional
	public City save(City city) {
		State state = stateService.getState(city.getState().getId());
		
		city.setState(state);
		
		return cityRepository.save(city);
	}
	
	@Transactional
	public void delete(Long cityId) {
		try {
			cityRepository.deleteById(cityId);
			cityRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new CityNotFoundException(cityId);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(CITY_IN_USE_MSG, cityId));
		}
	}
	
}
