package com.lunardi.marketstore.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "merchant_owner")
public class MerchantOwner extends User {
	
	@OneToMany(mappedBy = "merchantOwner", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Merchant> merchants;
	
}
