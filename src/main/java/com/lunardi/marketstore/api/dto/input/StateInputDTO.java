package com.lunardi.marketstore.api.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateInputDTO {

	@Size(min = 2, max = 2)
	@NotBlank
	private String name;
	
}
