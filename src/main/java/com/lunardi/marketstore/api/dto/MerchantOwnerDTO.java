package com.lunardi.marketstore.api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.lunardi.marketstore.api.dto.view.MerchantOwnerView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonView({MerchantOwnerView.class})
public class MerchantOwnerDTO extends UserDTO {
	
	private List<MerchantDTO> merchants;

}
