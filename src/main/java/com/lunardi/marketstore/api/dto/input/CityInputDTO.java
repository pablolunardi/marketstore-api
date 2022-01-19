package com.lunardi.marketstore.api.dto.input;

import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityInputDTO {

	@Size(min = 2, max = 2)
	private String name;
	
	@NotNull
	private Long stateId;
	
}
