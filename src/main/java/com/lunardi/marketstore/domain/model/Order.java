package com.lunardi.marketstore.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.lunardi.marketstore.domain.exception.BusinessException;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "[order]")
public class Order {
	
	@Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "order_status", nullable = false)
	private OrderStatus orderStatus;
	
	@Column(name = "order_sub_total", nullable = false)
	private BigDecimal orderSubTotal;
	
	@Column(name = "order_total", nullable = false)
	private BigDecimal orderTotal;
	
	@Column(name = "delivery_fee", nullable = false)
	private BigDecimal deliveryFee;
	
	@Column(name = "received_date", nullable = false)
	private OffsetDateTime receivedDate;
	
	@Column(name = "confirmed_date")
	private OffsetDateTime confirmedDate;
	
	@Column(name = "on_delivery_route_date")
	private OffsetDateTime onDeliveryRouteDate;
	
	@Column(name = "delivered_date")
	private OffsetDateTime deliveredDate;
	
	@Column(name = "cancelled_date")
	private OffsetDateTime cancelledDate;
	
	@Embedded
	@Column(nullable = false)
	private Address address;
	
	@ManyToOne
	@JoinColumn(name = "merchant_id", nullable = false)
	private Merchant merchant;
	
	@ManyToOne
	@JoinColumn(name = "payment_method_id", nullable = false)
	private PaymentMethod paymentMethod;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<OrderItem> items;
	
	public void calculateOrderTotal() {
		this.getItems().forEach(OrderItem::calculateOrderItemTotal);
		
		this.setOrderSubTotal(getItems().stream()
				.map(item -> item.getTotalPrice())
				.reduce(BigDecimal.ZERO, BigDecimal::add));
		
		this.setOrderTotal(this.getOrderSubTotal().add(deliveryFee));
	}

	public void confirm() {
		setStatus(OrderStatus.CONFIRMED);
		this.setConfirmedDate(OffsetDateTime.now());
	}
	
	private void setStatus(OrderStatus newStatus) {
		if (!this.getOrderStatus().canChangeToStatus(newStatus)) {
			throw new BusinessException(String.format("Can't change status of order %s from %s to %s", this.getId(),
					this.getOrderStatus(), newStatus));
		}
		
		this.setOrderStatus(newStatus);
	}
	
}
