package com.lunardi.marketstore.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lunardi.marketstore.domain.service.OrderFlowService;

@RestController
@RequestMapping("/orders/{orderId}")
public class OrderFlowController {
	
	@Autowired
	private OrderFlowService orderFlowService;

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("confirm")
	public void getOrder(@PathVariable Long orderId) {
		orderFlowService.confirm(orderId);
	}
	
}
