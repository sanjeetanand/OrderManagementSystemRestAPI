package com.psl.bean;

import java.sql.Date;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "order")
public class Order {

	private int orderId;
	private int custId;
	private Date orderDate;
	private Status orderStatus;
	private double orderCost;
	private Map<Product, Integer> orderProduct;
	
	public Order() {
		
	}

	public Order(int orderId, int custId, Date orderDate, Status orderStatus, double orderCost,
			Map<Product, Integer> orderProduct) {
		super();
		this.orderId = orderId;
		this.custId = custId;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.orderCost = orderCost;
		this.orderProduct = orderProduct;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Status getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Status orderStatus) {
		this.orderStatus = orderStatus;
	}

	public double getOrderCost() {
		return orderCost;
	}

	public void setOrderCost(double orderCost) {
		this.orderCost = orderCost;
	}

	public Map<Product, Integer> getOrderProduct() {
		return orderProduct;
	}

	public void setOrderProduct(Map<Product, Integer> orderProduct) {
		this.orderProduct = orderProduct;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", custId=" + custId + ", orderDate=" + orderDate + ", orderStatus="
				+ orderStatus + ", orderCost=" + orderCost + ", orderProduct=" + orderProduct + "]";
	}
	
}
