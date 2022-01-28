package com.lunardi.marketstore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lunardi.marketstore.domain.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
