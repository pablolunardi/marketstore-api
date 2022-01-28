package com.lunardi.marketstore.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lunardi.marketstore.domain.exception.EntityInUseException;
import com.lunardi.marketstore.domain.exception.PaymentMethodNotFoundException;
import com.lunardi.marketstore.domain.model.PaymentMethod;
import com.lunardi.marketstore.domain.repository.PaymentMethodRepository;

@Service
public class PaymentMethodService {
	
	private static final String PAYMENT_METHOD_IN_USE_MSG = "The paymentMethod with id %s can't be deleted because it's in use";
	
	@Autowired
	private PaymentMethodRepository paymentMethodRepository;

	public List<PaymentMethod> findAll() {
		return paymentMethodRepository.findAll();
	}

	public PaymentMethod getPaymentMethod(Long paymentMethodId) {
		return paymentMethodRepository.findById(paymentMethodId)
				.orElseThrow(() -> new PaymentMethodNotFoundException(paymentMethodId));
	}

	@Transactional
	public PaymentMethod save(PaymentMethod paymentMethod) {
		return paymentMethodRepository.save(paymentMethod);
	}
	
	@Transactional
	public void delete(Long paymentMethodId) {
		try {
			paymentMethodRepository.deleteById(paymentMethodId);
			paymentMethodRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new PaymentMethodNotFoundException(paymentMethodId);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(PAYMENT_METHOD_IN_USE_MSG, paymentMethodId));
		}
	}
	
}
