package com.lunardi.marketstore.api.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.lunardi.marketstore.domain.model.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class OrderDTO {

	private Long id;
	private OrderStatus orderStatus;
	private BigDecimal orderSubTotal;
	private BigDecimal orderTotal;
	private BigDecimal deliveryFee;
	private OffsetDateTime receivedDate;
	private OffsetDateTime confirmedDate;
	private OffsetDateTime onDeliveryRouteDate;
	private OffsetDateTime deliveredDate;
	private OffsetDateTime cancelledDate;
	private MerchantDTO merchant;
	private PaymentMethodDTO paymentMethod;
	private CustomerDTO customer;
	private List<OrderItemDTO> items;
	
}
