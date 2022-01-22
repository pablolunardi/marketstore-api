package com.lunardi.marketstore.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO {

	private Long id;
	private String name;
	private StateDTO state;
	
}
