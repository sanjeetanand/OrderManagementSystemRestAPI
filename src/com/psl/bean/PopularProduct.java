package com.psl.bean;

public class PopularProduct {

	private Product product;
	private int noOfCust;
	
	public PopularProduct() {
		super();
	}

	public PopularProduct(Product product, int noOfCust) {
		super();
		this.product = product;
		this.noOfCust = noOfCust;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getNoOfCust() {
		return noOfCust;
	}

	public void setNoOfCust(int noOfCust) {
		this.noOfCust = noOfCust;
	}
}
