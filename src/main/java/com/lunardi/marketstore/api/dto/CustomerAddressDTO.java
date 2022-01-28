package com.lunardi.marketstore.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddressDTO {

	private String zipCode;
	private String street1;
	private String street2;
	private String district;
	private CityDTO city;
	
}
