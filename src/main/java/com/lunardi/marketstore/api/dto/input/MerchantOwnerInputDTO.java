package com.lunardi.marketstore.api.dto.input;

import javax.validation.constraints.Email;
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
public class MerchantOwnerInputDTO {

	@Size(min = 5, max = 40)
	@NotBlank
	private String fullName;
	
	@Size(max = 40)
	@NotBlank
	@Email
	private String email;
	
	@Size(min = 10, max = 12)
	@NotBlank
	private String password;
	
}
