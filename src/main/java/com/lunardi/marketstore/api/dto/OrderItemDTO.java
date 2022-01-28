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
public class OrderItemDTO {

	private ProductDTO product;
	private BigDecimal unitPrice;
	private BigDecimal totalPrice;
	private Integer quantity;
	private String notes;
	
}
