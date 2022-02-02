package com.lunardi.marketstore.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	public Product findById(Long productId) {
		return productRepository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException(productId));
	}
	
	public Page<Product> findByMerchant(Long merchantId, Pageable pageable) {;
		return productRepository.findAllByMerchantId(merchantId, pageable);
	}

	public Product findByMerchantIdAndId(Long merchantId, Long productId) {
		return productRepository.findByMerchantIdAndId(merchantId, productId)
				.orElseThrow(() -> new ProductNotFoundException(productId));
	}

	@Transactional
	public Product save(Product product, Long merchantId) {
		Merchant merchant = merchantService.getMerchant(merchantId);
		
		product.setMerchant(merchant);
		
		return productRepository.save(product);
	}
	
	@Transactional
	public void delete(Long productId) {
		try {
			productRepository.deleteById(productId);
			productRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(PRODUCT_IN_USE_MSG, productId));
		}
	}
	
	
}
