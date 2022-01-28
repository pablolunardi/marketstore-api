package com.lunardi.marketstore.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class OrderItem {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "customer_order_id", nullable = false)
	private Order order;
	
	@Column(name = "unity_price", nullable = false)
	private BigDecimal unitPrice;
	
	@Column(name = "total_price", nullable = false)
	private BigDecimal totalPrice;
	
	@Column(nullable = false)
	private Integer quantity;
	
	@Column(length = 200)
	private String notes;
	
	public void calculateOrderItemTotal() {
		BigDecimal itemUnitPrice = this.getUnitPrice();
		Integer itemQuantity = this.getQuantity();
		
		if (itemUnitPrice == null) {
			itemUnitPrice = BigDecimal.ZERO;
		}
		
		if (itemQuantity == null) {
			itemQuantity = 0;
		}
		
		this.setTotalPrice(itemUnitPrice.multiply(new BigDecimal(itemQuantity)));
	}

}
