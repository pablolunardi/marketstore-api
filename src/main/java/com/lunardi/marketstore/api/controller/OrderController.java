package com.lunardi.marketstore.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.lunardi.marketstore.api.dto.OrderDTO;
import com.lunardi.marketstore.api.dto.input.OrderInputDTO;
import com.lunardi.marketstore.api.dto.view.OrderView;
import com.lunardi.marketstore.domain.exception.AddressNotFoundException;
import com.lunardi.marketstore.domain.exception.BusinessException;
import com.lunardi.marketstore.domain.exception.MerchantNotFoundException;
import com.lunardi.marketstore.domain.exception.PaymentMethodNotFoundException;
import com.lunardi.marketstore.domain.exception.ProductNotFoundException;
import com.lunardi.marketstore.domain.model.Order;
import com.lunardi.marketstore.domain.service.OrderEmitterService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderEmitterService orderEmitterService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@JsonView({OrderView.Summary.class})
	@GetMapping
	public Page<OrderDTO> findAll(Pageable pageable) {
		Page<Order> orders = orderEmitterService.findAll(pageable);
		
		return toDTOPage(orders, pageable);
	}

	@JsonView(OrderView.class)
	@GetMapping("/{orderId}")
	public OrderDTO getOrder(@PathVariable Long orderId) {
		return toDTO(orderEmitterService.getOrder(orderId));
	}
	
	@JsonView(OrderView.class)
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public OrderDTO create(@Valid @RequestBody OrderInputDTO orderInputDTO) {
		try {
			Order order = orderEmitterService.save(toModel(orderInputDTO), orderInputDTO.getAddress().getId());
			
			return toDTO(order);
		} catch (PaymentMethodNotFoundException | MerchantNotFoundException | ProductNotFoundException | AddressNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	
	private Page<OrderDTO> toDTOPage(Page<Order> orders, Pageable pageable) {
		List<OrderDTO> ordersDTO = orders.getContent().stream().map(this::toDTO)
				.collect(Collectors.toList());
		
		return new PageImpl<>(ordersDTO, pageable, ordersDTO.size());
	}
	
	private OrderDTO toDTO(Order order) {
		return modelMapper.map(order, OrderDTO.class);
	}
	
	private Order toModel(OrderInputDTO orderInputDTO) {
		return modelMapper.map(orderInputDTO, Order.class);
	}
	
}
