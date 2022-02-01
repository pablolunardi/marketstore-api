package com.lunardi.marketstore.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.lunardi.marketstore.api.dto.MerchantOwnerDTO;
import com.lunardi.marketstore.api.dto.input.MerchantOwnerInputDTO;
import com.lunardi.marketstore.api.dto.view.MerchantOwnerView;
import com.lunardi.marketstore.domain.model.MerchantOwner;
import com.lunardi.marketstore.domain.service.MerchantOwnerService;


@RestController
@RequestMapping("/merchant-owners")
public class MerchantOwnerController {
	
	@Autowired
	private  MerchantOwnerService merchantOwnerService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@JsonView(MerchantOwnerView.class)
	@GetMapping
	public List<MerchantOwnerDTO> findAll() {
		List<MerchantOwner> merchantOwners = merchantOwnerService.findAll();
		
		return toCollectionlDTO(merchantOwners);
	}
	
	@JsonView(MerchantOwnerView.class)
	@GetMapping("/{merchantOwnerId}")
	public MerchantOwnerDTO get(@PathVariable Long merchantOwnerId) {
		return toDTO(merchantOwnerService.getMerchantOwner(merchantOwnerId));
	}
	
	@JsonView(MerchantOwnerView.class)
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public MerchantOwnerDTO create(@Valid @RequestBody MerchantOwnerInputDTO merchantOwnerInputDTO) {
		MerchantOwner merchantOwner = merchantOwnerService.save(toModel(merchantOwnerInputDTO));
		
		return toDTO(merchantOwner);
	}
	
	@JsonView(MerchantOwnerView.class)
	@PutMapping("/{merchantOwnerId}")
	public MerchantOwnerDTO update(@PathVariable Long merchantOwnerId, @Valid @RequestBody MerchantOwnerInputDTO merchantOwnerInputDTO) {
		MerchantOwner merchantOwner = merchantOwnerService.getMerchantOwner(merchantOwnerId);
		
		modelMapper.map(merchantOwnerInputDTO, merchantOwner);
		
		merchantOwner = merchantOwnerService.save(merchantOwner);
				
		return toDTO(merchantOwner);
	}
	
	private List<MerchantOwnerDTO> toCollectionlDTO(List<MerchantOwner> merchantOwners) {
		return merchantOwners.stream().map(this::toDTO)
				.collect(Collectors.toList());
	}
	
	private MerchantOwnerDTO toDTO(MerchantOwner merchantOwner) {
		return modelMapper.map(merchantOwner, MerchantOwnerDTO.class);
	}
	
	private MerchantOwner toModel(MerchantOwnerInputDTO merchantOwnerInputDTO) {
		return modelMapper.map(merchantOwnerInputDTO, MerchantOwner.class);
	}
	
}
