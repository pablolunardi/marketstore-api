package com.lunardi.marketstore.api.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;
import com.lunardi.marketstore.api.dto.view.MerchantOwnerView;
import com.lunardi.marketstore.api.dto.view.MerchantView;
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

	@JsonView({MerchantView.class, MerchantOwnerView.class})
	private Long id;
	
	@JsonView({MerchantView.class, MerchantOwnerView.class})
	private Boolean active;
	
	@JsonView({MerchantView.class, MerchantOwnerView.class})
	private BigDecimal deliveryFee;
	
	@JsonView({MerchantView.class, MerchantOwnerView.class})
	private AddressDTO address;
	
	@JsonView({MerchantView.class, MerchantView.class, OrderView.class, OrderView.Summary.class})
	private String name;
	
	@JsonView({MerchantView.class})
	private MerchantOwnerDTO merchantOwner;
	
}
