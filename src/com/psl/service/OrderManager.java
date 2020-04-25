package com.psl.service;

import java.text.ParseException;
import java.util.List;
import com.psl.bean.Chart;
import com.psl.bean.Customer;
import com.psl.bean.Order;
import com.psl.bean.PopularProduct;
import com.psl.bean.Product;
import com.psl.dao.CustomerDao;
import com.psl.dao.OrderDao;
import com.psl.dao.ProductDao;

public class OrderManager {

	//Customer Service
	public List<Customer> getAllCustomer(){
		return new CustomerDao().getAllCustomer();
	}

	public Customer getCustomer(int custId) {
		return new CustomerDao().getCustomer(custId);
	}

	public boolean addCustomer(Customer cust) {
		return new CustomerDao().addCustomer(cust);
	}

	public boolean removeCustomer(int custId) {
		return new CustomerDao().removeCustomer(custId);
	}

	public boolean updateCustomer(Customer cust) {
		return new CustomerDao().updateCustomer(cust);
	}


	//Product Service
	public List<Product> getAllProduct(){
		return new ProductDao().getAllProduct();
	}

	public Product getProductById(int id){
		return new ProductDao().getProductById(id);
	}

	public Product getProductByName(String prodName){
		return new ProductDao().getProductByName(prodName);
	}

	public List<Product> getProductByCategory(String category){
		return new ProductDao().getProductByCategory(category);
	}

	public List<Product> getProductBelowQuantity(int quantity){
		return new ProductDao().getProductBelowQuantity(quantity);
	}

	public List<Product> getProductAboveQuantity(int quantity){
		return new ProductDao().getProductAboveQuantity(quantity);
	}

	public boolean removeProduct(int prodId){
		return new ProductDao().removeProduct(prodId);
	}

	public boolean updateProduct(Product p){
		return new ProductDao().updateProduct(p);
	}

	public boolean addProduct(Product p){
		return new ProductDao().addProduct(p);
	}

	//Order Services
	public List<Order> getAllOrder(){
		return new OrderDao().getAllOrder();
	}
	
	public boolean addOrder(Order order){
		return new OrderDao().addOrder(order);
	}
	
	public boolean updateOrder(Order order){
		return new OrderDao().updateOrder(order);
	}
	
	public boolean removeOrder(int orderId){
		return new OrderDao().removeOrder(orderId);
	}
	
	public Order getOrderById(int orderId){
		return new OrderDao().getOrderById(orderId);
	}
	
	public Order getOrderOfCust(int custId){
		return new OrderDao().getOrderById(custId);
	}
	
	public List<Order> getOrderByStatus(String status){
		return new OrderDao().getOrderByStatus(status);
	}
	
	public List<Order> getOrderBeforeDate(String date) throws ParseException{
		return new OrderDao().getOrderBeforeDate(date);
	}
	
	public List<Order> getOrderAfterDate(String date) throws ParseException{
		return new OrderDao().getOrderAfterDate(date);
	}
	
	public List<Order> getOrderBetweenDate(String startDate, String endDate) throws ParseException{
		return new OrderDao().getOrderBetweenDate(startDate,endDate);
	}
	
	public List<Order> getOrderOnDate(String date) throws ParseException{
		return new OrderDao().getOrderOnDate(date);
	}
	
	public List<PopularProduct> getPopularProducts(int month,int num) {
		return new OrderDao().getPopularProducts(month,num);
	}
	
	public List<Chart> getChart() {
		return new OrderDao().getChart();
	}
	
	public List<Chart> getChartByMonth(int month) {
		return new OrderDao().getChartByMonth(month);
	}


}
