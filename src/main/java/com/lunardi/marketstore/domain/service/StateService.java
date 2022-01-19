package com.lunardi.marketstore.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunardi.marketstore.domain.exception.StateNotFoundException;
import com.lunardi.marketstore.domain.model.State;
import com.lunardi.marketstore.domain.repository.StateRepository;

@Service
public class StateService {
	
	@Autowired
	private StateRepository statetRepository;
	
	public List<State> findAll() {
		return statetRepository.findAll();
	}

	public State getState(Long stateId) {
		return statetRepository.findById(stateId)
				.orElseThrow(() -> new StateNotFoundException(stateId));
	}

	public State save(State state) {
		return statetRepository.save(state);
	}
	
	public void delete(Long state) {
		statetRepository.deleteById(state);
	}
	
	
}
