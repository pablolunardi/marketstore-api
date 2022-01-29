package com.lunardi.marketstore.api.dto;

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
public class PaymentMethodDTO {

	@JsonView({OrderView.class, OrderView.Summary.class})
	private String description;
	
}
