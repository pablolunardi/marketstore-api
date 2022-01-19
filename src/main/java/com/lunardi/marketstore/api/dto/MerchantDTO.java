package com.lunardi.marketstore.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantDTO {

	private Long id;
	private String name;
	private Boolean active;
	private AddressDTO address;
	
}
