package com.lunardi.marketstore.api.dto.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressInputDTO {

	@Size(min = 8, max = 12)
	@NotBlank
	private String zipCode;
	
	@Size(min = 5, max = 30)
	@NotBlank
	private String street1;
	
	@Size(max = 10)
	private String street2;
	
	@Size(min = 3, max = 20)
	@NotBlank
	private String district;
	
	@Valid
	@NotNull
	private IdInputDTO city;
	
}
