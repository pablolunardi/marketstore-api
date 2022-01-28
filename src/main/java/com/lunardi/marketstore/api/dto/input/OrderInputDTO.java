package com.lunardi.marketstore.api.dto.input;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInputDTO {

	@NotNull
	@Valid
	IdInputDTO merchant;
	
	@NotNull
	@Valid
	IdInputDTO paymentMethod;
	
	@NotNull
	@Valid
	IdInputDTO address;
	
	@NotEmpty
	@Valid
	private List<OrderItemInputDTO> items;
	
}
