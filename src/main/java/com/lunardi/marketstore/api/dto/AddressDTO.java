package com.lunardi.marketstore.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
@JsonInclude(Include.NON_NULL)
@JsonView({MerchantView.class, MerchantOwnerView.class})
public class AddressDTO {

	private String zipCode;
	private String street1;
	private String street2;
	private String district;
	private CityDTO city;
	
}
