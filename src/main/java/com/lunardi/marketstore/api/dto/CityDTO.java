package com.lunardi.marketstore.api.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.lunardi.marketstore.api.dto.view.MerchantOwnerView;
import com.lunardi.marketstore.api.dto.view.MerchantView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonView({MerchantView.class, MerchantOwnerView.class})
public class CityDTO {

	private Long id;
	private String name;
	private StateDTO state;
	
}
