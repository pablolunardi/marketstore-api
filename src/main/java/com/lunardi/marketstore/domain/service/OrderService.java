package com.lunardi.marketstore.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lunardi.marketstore.domain.exception.OrderNotFoundException;
import com.lunardi.marketstore.domain.model.Address;
import com.lunardi.marketstore.domain.model.Customer;
import com.lunardi.marketstore.domain.model.CustomerAddress;
import com.lunardi.marketstore.domain.model.Merchant;
import com.lunardi.marketstore.domain.model.Order;
import com.lunardi.marketstore.domain.model.OrderItem;
import com.lunardi.marketstore.domain.model.OrderStatus;
import com.lunardi.marketstore.domain.model.PaymentMethod;
import com.lunardi.marketstore.domain.model.Product;
import com.lunardi.marketstore.domain.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private PaymentMethodService paymentMethodService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CustomerAddressService customerAddressService;
	
	@Autowired
	private CustomerService customerService;

	public Page<Order> findAll(Pageable pageable) {
		return orderRepository.findAll(pageable);
	}

	public Order getOrder(Long orderId) {
		return orderRepository.findById(orderId)
				.orElseThrow(() -> new OrderNotFoundException(orderId));
	}
	
	@Transactional
	public Order save(Order order, Long addressId) {
		Customer customer = customerService.findById(1L);
		order.setCustomer(customer);
		
		validateOrder(order, addressId);
		validateItems(order);
		
		order.setDeliveryFee(order.getMerchant().getDeliveryFee());
		order.setOrderStatus(OrderStatus.RECEIVED);
		order.setReceivedDate(OffsetDateTime.now());
		
		order.calculateOrderTotal();
		
		return orderRepository.save(order);
	}
	
	private void validateOrder(Order order, Long addressId) {
		PaymentMethod paymentMethod = paymentMethodService.getPaymentMethod(order.getPaymentMethod().getId());
		Merchant merchant = merchantService.getMerchant(order.getMerchant().getId());
		CustomerAddress customerAddress = customerAddressService.getCustomerAddress(addressId, order.getCustomer().getId());
		
		order.setMerchant(merchant);
		order.setPaymentMethod(paymentMethod);
		order.setAddress(buildCustomerAddress(customerAddress));
	}
	
	private Address buildCustomerAddress(CustomerAddress customerAddress) {
		Address address = new Address();
		address.setCity(customerAddress.getCity());
		address.setDistrict(customerAddress.getDistrict());
		address.setStreet1(customerAddress.getStreet1());
		address.setStreet2(customerAddress.getStreet2());
		address.setZipCode(customerAddress.getZipCode());
		
		return address;
	}

	private void validateItems(Order order) {
		for (OrderItem orderItem : order.getItems()) {
			Product product = productService.findByMerchantIdAndId(order.getMerchant().getId(), orderItem.getProduct().getId());
			
			orderItem.setOrder(order);
			orderItem.setUnitPrice(product.getPrice());
			orderItem.setProduct(product);
		}
		
	}
	
}
