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
public class MerchantDTO {

	private Long id;
	
	private Boolean active;
	
	private BigDecimal deliveryFee;
	
	private AddressDTO address;
	
	@JsonView({OrderView.class, OrderView.Summary.class})
	private String name;
	
}
