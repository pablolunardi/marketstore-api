package com.lunardi.marketstore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lunardi.marketstore.domain.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
