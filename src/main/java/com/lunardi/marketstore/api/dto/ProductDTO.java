package com.lunardi.marketstore.api.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	private Boolean enabled;
	private MerchantDTO merchant;
	
}
