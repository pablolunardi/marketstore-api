package com.lunardi.marketstore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lunardi.marketstore.domain.model.Merchant;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {

}
