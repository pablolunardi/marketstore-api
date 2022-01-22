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

import com.lunardi.marketstore.api.dto.CityDTO;
import com.lunardi.marketstore.api.dto.input.CityInputDTO;
import com.lunardi.marketstore.domain.exception.BusinessException;
import com.lunardi.marketstore.domain.exception.StateNotFoundException;
import com.lunardi.marketstore.domain.model.City;
import com.lunardi.marketstore.domain.model.State;
import com.lunardi.marketstore.domain.service.CityService;


@RestController
@RequestMapping("/cities")
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<CityDTO> findAll() {
		List<City> cities = cityService.findAll();
		
		return toCollectionlDTO(cities);
	}
	
	@GetMapping("/{cityId}")
	public CityDTO getCity(@PathVariable Long cityId) {
		return toDTO(cityService.getCity(cityId));
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public CityDTO create(@Valid @RequestBody CityInputDTO cityInputDTO) {
		try {
			City city = cityService.save(toModel(cityInputDTO));
			
			return toDTO(city);
		} catch (StateNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{cityId}")
	public CityDTO update(@PathVariable Long cityId, @Valid @RequestBody CityInputDTO cityInputDTO) {
		try {
			City city = cityService.getCity(cityId);
			city.setState(new State());
			
			modelMapper.map(cityInputDTO, city);
			
			city = cityService.save(city);
					
			return toDTO(city);
		} catch (StateNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{cityId}")
	public void delete(@PathVariable Long cityId) {
		cityService.delete(cityId);
	}
	
	private List<CityDTO> toCollectionlDTO(List<City> cities) {
		return cities.stream().map(this::toDTO)
				.collect(Collectors.toList());
	}
	
	private CityDTO toDTO(City city) {
		return modelMapper.map(city, CityDTO.class);
	}
	
	private City toModel(CityInputDTO cityInputDTO) {
		return modelMapper.map(cityInputDTO, City.class);
	}
	
}
