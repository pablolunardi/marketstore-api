package com.lunardi.marketstore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lunardi.marketstore.domain.model.State;

public interface StateRepository extends JpaRepository<State, Long> {

}
