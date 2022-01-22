package com.lunardi.marketstore.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lunardi.marketstore.domain.exception.EntityInUseException;
import com.lunardi.marketstore.domain.exception.ProductNotFoundException;
import com.lunardi.marketstore.domain.model.Merchant;
import com.lunardi.marketstore.domain.model.Product;
import com.lunardi.marketstore.domain.repository.ProductRepository;

@Service
public class ProductService {
	
	private static final String PRODUCT_IN_USE_MSG = "The product with id %s can't be deleted because it's in use";
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private MerchantService merchantService;
	
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Product getProduct(Long productId) {
		return productRepository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException(productId));
	}

	@Transactional
	public Product save(Product product) {
		Merchant merchant = merchantService.getMerchant(product.getMerchant().getId());
		
		product.setMerchant(merchant);
		
		return productRepository.save(product);
	}
	
	@Transactional
	public void delete(Long productId) {
		try {
			productRepository.deleteById(productId);
			productRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new ProductNotFoundException(productId);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(PRODUCT_IN_USE_MSG, productId));
		}
	}
	
	
}
