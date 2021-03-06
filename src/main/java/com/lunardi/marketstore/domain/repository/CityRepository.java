package com.lunardi.marketstore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lunardi.marketstore.domain.model.City;

public interface CityRepository extends JpaRepository<City, Long> {

}
