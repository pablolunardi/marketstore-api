package com.lunardi.marketstore.api.dto.input;

import javax.validation.Valid;
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
public class OrderItemInputDTO {

	@NotNull
	@Valid
	private IdInputDTO product;
	
	@NotNull
	private Integer quantity;
	
	@Size(max = 200)
	private String notes;
	
}
