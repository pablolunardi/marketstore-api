package com.lunardi.marketstore.api.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.lunardi.marketstore.api.dto.view.MerchantOwnerView;
import com.lunardi.marketstore.api.dto.view.MerchantView;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonView({MerchantOwnerView.class, MerchantView.class})
public class UserDTO {

	private Long id;
	private String fullName;
	private String email;

}
