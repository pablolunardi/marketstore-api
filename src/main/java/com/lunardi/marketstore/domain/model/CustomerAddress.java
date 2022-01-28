package com.lunardi.marketstore.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "customer_address")
public class CustomerAddress {
	
	@Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "address_zipcode", nullable = false, length = 12)
	private String zipCode;
	
	@Column(name = "address_street_1", nullable = false, length = 30)
	private String street1;
	
	@Column(name = "address_street_2", length = 10)
	private String street2;
	
	@Column(name = "address_district", nullable = false, length = 20)
	private String district;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_address_city_id", nullable =  false)
	private City city;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_address_customer_id", nullable = false)
	private Customer customer;
	
}
