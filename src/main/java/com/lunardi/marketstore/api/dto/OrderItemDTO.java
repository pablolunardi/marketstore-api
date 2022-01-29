package com.lunardi.marketstore.api.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;
import com.lunardi.marketstore.api.dto.view.OrderView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonView({OrderView.class})
public class OrderItemDTO {

	private ProductDTO product;
	private BigDecimal unitPrice;
	private BigDecimal totalPrice;
	private Integer quantity;
	private String notes;
	
}
