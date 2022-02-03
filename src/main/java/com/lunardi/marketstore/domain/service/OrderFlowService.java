package com.lunardi.marketstore.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunardi.marketstore.domain.model.Order;
import com.lunardi.marketstore.domain.repository.OrderRepository;

@Service
public class OrderFlowService {

	@Autowired
	private OrderEmitterService orderEmitterService;
	
	@Autowired
	private OrderRepository orderRepository;

	@Transactional
	public void confirm(Long orderId) {
		Order order = orderEmitterService.getOrder(orderId);
		
		order.confirm();
		
		orderRepository.save(order);
	}

	
}
