package com.lunardi.marketstore.api.dto.input;

import java.math.BigDecimal;

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
public class ProductInputDTO {

	@Size(min = 3, max = 40)
	private String name;
	
	@Size(min = 15, max = 80)
	private String description;
	
	@NotNull
	private BigDecimal price;
	
	@NotNull
	private Long merchantId;
	
}
