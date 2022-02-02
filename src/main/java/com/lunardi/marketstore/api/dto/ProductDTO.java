package com.lunardi.marketstore.api.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;
import com.lunardi.marketstore.api.dto.view.OrderView;
import com.lunardi.marketstore.api.dto.view.ProductView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	@JsonView({ProductView.class, OrderView.class})
	private Long id;
	
	@JsonView({ProductView.class, OrderView.class})
	private String name;
	
	@JsonView({ProductView.class, OrderView.class})
	private String description;
	
	@JsonView({ProductView.class})
	private BigDecimal price;
	
	@JsonView({ProductView.class})
	private Boolean enabled;
	
}
