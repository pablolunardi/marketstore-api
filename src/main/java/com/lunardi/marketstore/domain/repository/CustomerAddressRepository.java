package com.lunardi.marketstore.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lunardi.marketstore.domain.model.CustomerAddress;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {

	Optional<CustomerAddress> findByIdAndCustomerId(Long customerAddressId, Long customerId);

	List<CustomerAddress> findByCustomerId(Long customerId);

}
