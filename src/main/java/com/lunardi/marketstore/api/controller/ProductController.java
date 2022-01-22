package com.lunardi.marketstore.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.lunardi.marketstore.api.dto.ProductDTO;
import com.lunardi.marketstore.api.dto.input.ProductInputDTO;
import com.lunardi.marketstore.domain.exception.BusinessException;
import com.lunardi.marketstore.domain.exception.MerchantNotFoundException;
import com.lunardi.marketstore.domain.model.Merchant;
import com.lunardi.marketstore.domain.model.Product;
import com.lunardi.marketstore.domain.service.ProductService;


@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<ProductDTO> findAll() {
		List<Product> products = productService.findAll();
		
		return toCollectionlDTO(products);
	}
	
	@GetMapping("/{productId}")
	public ProductDTO getProduct(@PathVariable Long productId) {
		return toDTO(productService.getProduct(productId));
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public ProductDTO create(@Valid @RequestBody ProductInputDTO productInputDTO) {
		try {
			Product product = productService.save(toModel(productInputDTO));
			
			return toDTO(product);
		} catch (MerchantNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{productId}")
	public ProductDTO update(@PathVariable Long productId, @Valid @RequestBody ProductInputDTO productInputDTO) {
		try {
			Product product = productService.getProduct(productId);
			product.setMerchant(new Merchant());
			
			modelMapper.map(productInputDTO, product);
			
			product = productService.save(product);
					
			return toDTO(product);
		} catch (MerchantNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{productId}")
	public void delete(@PathVariable Long productId) {
		productService.delete(productId);
	}
	
	private List<ProductDTO> toCollectionlDTO(List<Product> products) {
		return products.stream().map(this::toDTO)
				.collect(Collectors.toList());
	}
	
	private ProductDTO toDTO(Product product) {
		return modelMapper.map(product, ProductDTO.class);
	}
	
	private Product toModel(ProductInputDTO productInputDTO) {
		return modelMapper.map(productInputDTO, Product.class);
	}
	
}
