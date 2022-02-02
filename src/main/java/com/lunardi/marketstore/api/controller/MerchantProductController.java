package com.lunardi.marketstore.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.lunardi.marketstore.api.dto.ProductDTO;
import com.lunardi.marketstore.api.dto.input.ProductInputDTO;
import com.lunardi.marketstore.api.dto.view.ProductView;
import com.lunardi.marketstore.domain.exception.BusinessException;
import com.lunardi.marketstore.domain.exception.MerchantNotFoundException;
import com.lunardi.marketstore.domain.model.Product;
import com.lunardi.marketstore.domain.service.ProductService;


@RestController
@RequestMapping("/merchants/{merchantId}/products")
public class MerchantProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@JsonView({ProductView.class})
	@GetMapping
	public Page<ProductDTO> findAll(@PathVariable Long merchantId, Pageable pageable) {
		Page<Product> products = productService.findByMerchant(merchantId, pageable);
		
		return toDTOPage(products, pageable);
	}
	
	@GetMapping("/{productId}")
	public ProductDTO getProduct(@PathVariable Long merchantId, @PathVariable Long productId) {
		return toDTO(productService.findByMerchantIdAndId(merchantId, productId));
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public ProductDTO create(@PathVariable Long merchantId, @Valid @RequestBody ProductInputDTO productInputDTO) {
		try {
			Product product = productService.save(toModel(productInputDTO), merchantId);
			
			return toDTO(product);
		} catch (MerchantNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{productId}")
	public ProductDTO update(@PathVariable Long merchantId, @PathVariable Long productId, @Valid @RequestBody ProductInputDTO productInputDTO) {
		try {
			Product product = productService.findByMerchantIdAndId(merchantId, productId);
			
			modelMapper.map(productInputDTO, product);
			
			product = productService.save(product, merchantId);
					
			return toDTO(product);
		} catch (MerchantNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{productId}")
	public void delete(@PathVariable Long merchantId, @PathVariable Long productId) {
		Product product = productService.findByMerchantIdAndId(merchantId, productId);
		
		productService.delete(product.getId());
	}
	
	private Page<ProductDTO> toDTOPage(Page<Product> products, Pageable pageable) {
		List<ProductDTO> productsDTO = products.getContent().stream().map(this::toDTO)
				.collect(Collectors.toList());
		
		return new PageImpl<>(productsDTO, pageable, productsDTO.size());
	}
	
	private ProductDTO toDTO(Product product) {
		return modelMapper.map(product, ProductDTO.class);
	}
	
	private Product toModel(ProductInputDTO productInputDTO) {
		return modelMapper.map(productInputDTO, Product.class);
	}
	
}
