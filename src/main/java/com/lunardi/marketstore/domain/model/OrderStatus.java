package com.lunardi.marketstore.domain.model;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public enum OrderStatus {
	
	RECEIVED,
	CONFIRMED(RECEIVED),
	ON_DELIVERY_ROUTE(CONFIRMED),
	DELIVERED(ON_DELIVERY_ROUTE),
	CANCELLED(RECEIVED, CONFIRMED);
	
	private List<OrderStatus> previousStatuses;

	OrderStatus(OrderStatus... previousStatuses) {
		this.previousStatuses = Arrays.asList(previousStatuses);
	}
	
	public boolean canChangeToStatus(OrderStatus newStatus) {
		return newStatus.getPreviousStatuses().contains(this);
	}

}
