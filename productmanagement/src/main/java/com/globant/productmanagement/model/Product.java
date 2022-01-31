package com.globant.productmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Product")
public class Product {
	
	@Id
	@Column
	private int id;
	@Column
	private String name;
	@Column
	private double price;
	@Column
	private int stock;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(int id, String name, double price,int stock) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock=stock;
	}
	
	

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", stock=" + stock + "]";
	}
	
	
}
