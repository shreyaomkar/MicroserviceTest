package com.globant.productmanagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globant.productmanagement.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>
{
	
}

