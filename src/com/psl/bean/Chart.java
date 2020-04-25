package com.psl.bean;

import java.util.Map;

public class Chart {
	private Category category;
	private Map<Product, Integer> sales;
	public Chart() {
		super();
	}
	public Chart(Category category, Map<Product, Integer> sales) {
		super();
		this.category = category;
		this.sales = sales;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Map<Product, Integer> getSales() {
		return sales;
	}
	public void setSales(Map<Product, Integer> sales) {
		this.sales = sales;
	}
}
