package com.lunardi.marketstore.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunardi.marketstore.domain.exception.ProductNotFoundException;
import com.lunardi.marketstore.domain.model.Product;
import com.lunardi.marketstore.domain.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Product getProduct(Long productId) {
		return productRepository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException(productId));
	}

	public Product save(Product product) {
		return productRepository.save(product);
	}
	
	public void delete(Long productId) {
		productRepository.deleteById(productId);
	}
	
	
}
