package com.lunardi.marketstore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lunardi.marketstore.domain.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
