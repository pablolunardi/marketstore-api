package com.lunardi.marketstore.api.dto;

import com.lunardi.marketstore.domain.model.State;

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
	private State state;
	
}
