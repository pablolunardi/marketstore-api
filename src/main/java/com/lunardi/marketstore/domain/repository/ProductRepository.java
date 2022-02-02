package com.lunardi.marketstore.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lunardi.marketstore.domain.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Optional<Product> findByMerchantIdAndId(Long merchantId, Long productId);

	Page<Product> findAllByMerchantId(Long merchantId, Pageable pageable);

}
