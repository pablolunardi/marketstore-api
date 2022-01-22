package com.lunardi.marketstore.api.dto.input;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityIdInputDTO {
	
	@NotNull
	Long id;

}
