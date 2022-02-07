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
	
	@Transactional
	public void cancel(Long orderId) {
		Order order = orderEmitterService.getOrder(orderId);
		
		order.cancel();
		
		orderRepository.save(order);
	}
	
	@Transactional
	public void deliver(Long orderId) {
		Order order = orderEmitterService.getOrder(orderId);
		
		order.deliver();
		
		orderRepository.save(order);
	}
	
	@Transactional
	public void complete(Long orderId) {
		Order order = orderEmitterService.getOrder(orderId);
		
		order.complete();
		
		orderRepository.save(order);
	}

	
}
