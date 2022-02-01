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

import com.fasterxml.jackson.annotation.JsonView;
import com.lunardi.marketstore.api.dto.MerchantDTO;
import com.lunardi.marketstore.api.dto.input.MerchantInputDTO;
import com.lunardi.marketstore.api.dto.view.MerchantView;
import com.lunardi.marketstore.domain.exception.BusinessException;
import com.lunardi.marketstore.domain.exception.CityNotFoundException;
import com.lunardi.marketstore.domain.model.City;
import com.lunardi.marketstore.domain.model.Merchant;
import com.lunardi.marketstore.domain.service.MerchantService;


@RestController
@RequestMapping("/merchants")
public class MerchantController {
	
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@JsonView({MerchantView.class})
	@GetMapping
	public List<MerchantDTO> findAll() {
		List<Merchant> merchants = merchantService.findAll();
		
		return toCollectionlDTO(merchants);
	}
	
	@JsonView({MerchantView.class})
	@GetMapping("/{merchantId}")
	public MerchantDTO getMerchant(@PathVariable Long merchantId) {
		return toDTO(merchantService.getMerchant(merchantId));
	}
	
	@JsonView({MerchantView.class})
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public MerchantDTO create(@Valid @RequestBody MerchantInputDTO merchantInputDTO) {
		try {
			Merchant merchant = merchantService.save(toModel(merchantInputDTO));
			
			return toDTO(merchant);
		} catch (CityNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@JsonView({MerchantView.class})
	@PutMapping("/{merchantId}")
	public MerchantDTO update(@PathVariable Long merchantId, @Valid @RequestBody MerchantInputDTO merchantInputDTO) {
		try {
			Merchant merchant = merchantService.getMerchant(merchantId);
			merchant.getAddress().setCity(new City());
			
			modelMapper.map(merchantInputDTO, merchant);
			
			merchant = merchantService.save(merchant);
					
			return toDTO(merchant);
		} catch (CityNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{merchantId}")
	public void delete(@PathVariable Long merchantId) {
		merchantService.delete(merchantId);
	}
	
	private List<MerchantDTO> toCollectionlDTO(List<Merchant> merchants) {
		return merchants.stream().map(this::toDTO)
				.collect(Collectors.toList());
	}
	
	private MerchantDTO toDTO(Merchant merchant) {
		return modelMapper.map(merchant, MerchantDTO.class);
	}
	
	private Merchant toModel(MerchantInputDTO merchantInputDTO) {
		return modelMapper.map(merchantInputDTO, Merchant.class);
	}
	
}
