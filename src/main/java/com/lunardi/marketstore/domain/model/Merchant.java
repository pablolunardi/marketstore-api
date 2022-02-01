package com.lunardi.marketstore.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Merchant {
	
	@Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 30)
	private String name;
	
	@Column(nullable = false)
	private Boolean active;
	
	@Column(nullable = false)
	private BigDecimal deliveryFee;
	
	@Embedded
	@Column(nullable = false)
	private Address address;
	
	@ManyToOne
	@JoinColumn(name =  "merchant_owner_id", nullable = false)
	private MerchantOwner merchantOwner; 

}
