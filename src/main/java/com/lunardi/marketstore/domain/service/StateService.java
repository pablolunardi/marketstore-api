package com.lunardi.marketstore.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lunardi.marketstore.domain.exception.EntityInUseException;
import com.lunardi.marketstore.domain.exception.StateNotFoundException;
import com.lunardi.marketstore.domain.model.State;
import com.lunardi.marketstore.domain.repository.StateRepository;

@Service
public class StateService {

	private static final String STATE_IN_USE_MSG = "The state with id %s can't be deleted because it's in use";
	
	@Autowired
	private StateRepository stateRepository;
	
	public List<State> findAll() {
		return stateRepository.findAll();
	}

	public State getState(Long stateId) {
		return stateRepository.findById(stateId)
				.orElseThrow(() -> new StateNotFoundException(stateId));
	}
	
	@Transactional
	public State save(State state) {
		return stateRepository.save(state);
	}
	
	@Transactional
	public void delete(Long stateId) {
		try {
			stateRepository.deleteById(stateId);
			stateRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new StateNotFoundException(stateId);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(STATE_IN_USE_MSG, stateId));
		}
	}
	
	
}
