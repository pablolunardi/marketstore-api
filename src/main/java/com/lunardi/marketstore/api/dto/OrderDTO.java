package com.lunardi.marketstore.api.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonView;
import com.lunardi.marketstore.api.dto.view.OrderView;
import com.lunardi.marketstore.domain.model.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonView({OrderView.class})
public class OrderDTO {

	@JsonView({OrderView.class, OrderView.Summary.class})
	private Long id;
	
	@JsonView({OrderView.class, OrderView.Summary.class})
	private OrderStatus orderStatus;
	
	@JsonView({OrderView.class, OrderView.Summary.class})
	private BigDecimal orderSubTotal;
	
	@JsonView({OrderView.class, OrderView.Summary.class})
	private BigDecimal orderTotal;
	
	@JsonView({OrderView.class, OrderView.Summary.class})
	private BigDecimal deliveryFee;
	
	@JsonView({OrderView.class, OrderView.Summary.class})
	private OffsetDateTime receivedDate;
	
	@JsonView({OrderView.class, OrderView.Summary.class})
	private OffsetDateTime confirmedDate;
	
	@JsonView({OrderView.class, OrderView.Summary.class})
	private OffsetDateTime onDeliveryRouteDate;
	
	@JsonView({OrderView.class, OrderView.Summary.class})
	private OffsetDateTime deliveredDate;
	
	@JsonView({OrderView.class, OrderView.Summary.class})
	private OffsetDateTime cancelledDate;
	
	@JsonView({OrderView.class, OrderView.Summary.class})
	private MerchantDTO merchant;
	
	@JsonView({OrderView.class, OrderView.Summary.class})
	private PaymentMethodDTO paymentMethod;
	
	private CustomerDTO customer;
	
	private List<OrderItemDTO> items;
	
}
