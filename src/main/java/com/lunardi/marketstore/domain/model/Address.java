package com.lunardi.marketstore.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Embeddable
public class Address {
	
	@Column(name = "address_zipcode", nullable = false, length = 12)
	private String zipCode;
	
	@Column(name = "address_street_1", nullable = false, length = 30)
	private String street1;
	
	@Column(name = "address_street_2", length = 10)
	private String street2;
	
	@Column(name = "address_district", nullable = false, length = 20)
	private String district;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_city_id", nullable =  false)
	private City city;

}
