package com.globant.productmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globant.productmanagement.model.Product;
import com.globant.productmanagement.service.ProductService;

@RestController
public class ProductController 
{
	
	@Autowired
	private ProductService productservice;
	
	@RequestMapping("/home")
	@GetMapping
	public List<Product> showHome(Model model)
	{
		List<Product> productList=new ArrayList<Product>();
		productList=productservice.productList();
		model.addAttribute("productList", productList);
		return productservice.productList();
	}
	
}


