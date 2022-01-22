package com.lunardi.marketstore.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lunardi.marketstore.api.dto.StateDTO;
import com.lunardi.marketstore.api.dto.input.StateInputDTO;
import com.lunardi.marketstore.domain.model.State;
import com.lunardi.marketstore.domain.service.StateService;


@RestController
@RequestMapping("/states")
public class StateController {
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<StateDTO> findAll() {
		List<State> states = stateService.findAll();
		
		return toCollectionlDTO(states);
	}
	
	@GetMapping("/{stateId}")
	public StateDTO getState(@PathVariable Long stateId) {
		return toDTO(stateService.getState(stateId));
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public StateDTO create(@Valid @RequestBody StateInputDTO stateInputDTO) {
		State state = stateService.save(toModel(stateInputDTO));
		
		return toDTO(state);
	}
	
	@PutMapping("/{stateId}")
	public StateDTO update(@PathVariable Long stateId, @Valid @RequestBody StateInputDTO stateInputDTO) {
		State state = stateService.getState(stateId);
		
		modelMapper.map(stateInputDTO, state);
		
		state = stateService.save(state);
				
		return toDTO(state);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{stateId}")
	public void delete(@PathVariable Long stateId) {
		stateService.delete(stateId);
	}
	
	private List<StateDTO> toCollectionlDTO(List<State> states) {
		return states.stream().map(this::toDTO)
				.collect(Collectors.toList());
	}
	
	private StateDTO toDTO(State state) {
		return modelMapper.map(state, StateDTO.class);
	}
	
	private State toModel(StateInputDTO stateInputDTO) {
		return modelMapper.map(stateInputDTO, State.class);
	}
	
}
