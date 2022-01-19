package com.lunardi.marketstore.api.dto.input;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.lunardi.marketstore.domain.model.City;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantInputDTO {

	@Size(min = 3, max = 30)
	private String name;
	
	@NotNull
	private Boolean active;
	
	@Size(min = 10, max = 12)
	private String addressZipCode;
	
	@Size(min = 5, max = 30)
	private String addressStreet1;
	
	@Size(min = 5, max = 20)
	private String addressDistrict;
	
	private String addressStreet2;
	private City cityId;

	
}
