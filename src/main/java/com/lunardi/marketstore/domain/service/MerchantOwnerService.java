package com.lunardi.marketstore.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lunardi.marketstore.domain.exception.MerchantOwnerNotFoundException;
import com.lunardi.marketstore.domain.model.MerchantOwner;
import com.lunardi.marketstore.domain.repository.MerchantOwnerRepository;

@Service
public class MerchantOwnerService {

	@Autowired
	private MerchantOwnerRepository merchantOwnerRepository;
	
	public Page<MerchantOwner> findAll(Pageable pageable) {
		return merchantOwnerRepository.findAll(pageable);
	}
	
	public MerchantOwner findById(Long merchantOwnerId) {
		return merchantOwnerRepository.findById(merchantOwnerId)
				.orElseThrow(() -> new MerchantOwnerNotFoundException(merchantOwnerId));
	}

	public MerchantOwner getMerchantOwner(Long merchantOwnerId) {
		return merchantOwnerRepository.findById(merchantOwnerId)
				.orElseThrow(() -> new MerchantOwnerNotFoundException(merchantOwnerId));
	}
	
	@Transactional
	public MerchantOwner save(MerchantOwner merchantOwner) {
		return merchantOwnerRepository.save(merchantOwner);
	}
	
}
