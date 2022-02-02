package com.lunardi.marketstore.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lunardi.marketstore.domain.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query(value = "select o from Order o join fetch o.paymentMethod join fetch o.customer join fetch o.merchant join fetch o.items i"
			+ " join fetch i.product join fetch o.merchant m join fetch m.merchantOwner mo join fetch mo.merchants"
			+ " join fetch o.address.city c join fetch c.state",
			countQuery = "select count (o) from Order o")
	Page<Order> findAll(Pageable pageable);

}
