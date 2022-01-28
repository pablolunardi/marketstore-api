package com.lunardi.marketstore.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lunardi.marketstore.domain.model.Merchant;
import com.lunardi.marketstore.domain.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findAllByMerchant(Merchant merchant);
	
	Optional<Product> findByMerchantIdAndId(Long merchantId, Long productId);

}
