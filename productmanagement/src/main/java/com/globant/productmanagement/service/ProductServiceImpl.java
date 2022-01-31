package com.globant.productmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.productmanagement.ProductRepository;
import com.globant.productmanagement.model.Product;

@Service
public class ProductServiceImpl implements ProductService
{
	
	@Autowired
	private ProductRepository productRepository ;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository=productRepository;
	}

	public List<Product> productList()
	{
		 return productRepository.findAll();
		 
	}
}

