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
public class MerchantInputDTO {

	@Size(min = 3, max = 30)
	@NotBlank
	private String name;
	
	@NotNull
	private Boolean active;
	
	@NotNull
	@Valid
	private AddressInputDTO address;
	
}
