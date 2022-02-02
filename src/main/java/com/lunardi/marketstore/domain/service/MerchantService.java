package com.lunardi.marketstore.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lunardi.marketstore.domain.exception.EntityInUseException;
import com.lunardi.marketstore.domain.exception.MerchantNotFoundException;
import com.lunardi.marketstore.domain.model.City;
import com.lunardi.marketstore.domain.model.Merchant;
import com.lunardi.marketstore.domain.repository.MerchantRepository;

@Service
public class MerchantService {

	private static final String MERCHANT_IN_USE_MSG = "The merchant with id %s can't be deleted because it's in use";
	
	@Autowired
	private MerchantRepository merchantRepository;
	
	@Autowired
	private CityService cityService;
	
	public Page<Merchant> findAll(Pageable pageable) {
		return merchantRepository.findAll(pageable);
	}

	public Merchant getMerchant(Long merchantId) {
		return merchantRepository.findById(merchantId)
				.orElseThrow(() -> new MerchantNotFoundException(merchantId));
	}
	
	@Transactional
	public Merchant save(Merchant merchant) {
		City city = cityService.getCity(merchant.getAddress().getCity().getId());
		
		merchant.getAddress().setCity(city);
		
		return merchantRepository.save(merchant);
	}
	
	@Transactional
	public void delete(Long merchantId) {
		try {
			merchantRepository.deleteById(merchantId);
			merchantRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new MerchantNotFoundException(merchantId);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(MERCHANT_IN_USE_MSG, merchantId));
		}
	}
	
	
}
