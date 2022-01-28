package com.lunardi.marketstore.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lunardi.marketstore.api.dto.OrderDTO;
import com.lunardi.marketstore.api.dto.input.OrderInputDTO;
import com.lunardi.marketstore.domain.exception.BusinessException;
import com.lunardi.marketstore.domain.exception.MerchantNotFoundException;
import com.lunardi.marketstore.domain.exception.PaymentMethodNotFoundException;
import com.lunardi.marketstore.domain.model.Order;
import com.lunardi.marketstore.domain.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<OrderDTO> findAll() {
		List<Order> orders = orderService.findAll();
		
		return toCollectionlDTO(orders);
	}
	
	@GetMapping("/{orderId}")
	public OrderDTO getCity(@PathVariable Long orderId) {
		return toDTO(orderService.getOrder(orderId));
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public OrderDTO create(@Valid @RequestBody OrderInputDTO orderInputDTO) {
		try {
			Order order = orderService.save(toModel(orderInputDTO), orderInputDTO.getAddress().getId());
			
			return toDTO(order);
		} catch (PaymentMethodNotFoundException | MerchantNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	
	private List<OrderDTO> toCollectionlDTO(List<Order> orders) {
		return orders.stream().map(this::toDTO)
				.collect(Collectors.toList());
	}
	
	private OrderDTO toDTO(Order order) {
		return modelMapper.map(order, OrderDTO.class);
	}
	
	private Order toModel(OrderInputDTO orderInputDTO) {
		return modelMapper.map(orderInputDTO, Order.class);
	}
	
}
