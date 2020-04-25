package com.psl.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.psl.bean.Product;
import com.psl.service.OrderManager;

@Path("/product")
public class ProductResource {

	private static final String SUCCESS_RESULT="{\"result\":\"success\"}";
	private static final String FAILURE_RESULT="{\"result\":\"fail\"}";

	
	@Path("/getAllProduct")
	@GET
	@Produces(value = {MediaType.APPLICATION_JSON})
	public List<Product> getAllProduct(){
		List<Product> p = new OrderManager().getAllProduct();
		return p;
	}
	
	@Path("/getProductById")
	@GET
	@Produces(value = {MediaType.APPLICATION_JSON})
	public Product getProductById(@QueryParam("pid") int prodId){
		Product p = new OrderManager().getProductById(prodId);
		return p;
	}
	
	@Path("/getProductByName")
	@GET
	@Produces(value = {MediaType.APPLICATION_JSON})
	public Product getProductByName(@QueryParam("pname") String prodName){
		Product p = new OrderManager().getProductByName(prodName);
		return p;
	}

	@Path("/getProductByCat")
	@GET
	@Produces(value = {MediaType.APPLICATION_JSON})
	public List<Product> getProductByCategory(@QueryParam("pcat") String category){
		List<Product> p = new OrderManager().getProductByCategory(category);
		return p;
	}
	
	@Path("/getProductBQ")
	@GET
	@Produces(value = {MediaType.APPLICATION_JSON})
	public List<Product> getProductBelowQuantity(@QueryParam("pq") int quantity){
		List<Product> p = new OrderManager().getProductBelowQuantity(quantity);
		return p;
	}
	
	@Path("/getProductAQ")
	@GET
	@Produces(value = {MediaType.APPLICATION_JSON})
	public List<Product> getProductAboveQuantity(@QueryParam("pq") int quantity){
		List<Product> p = new OrderManager().getProductAboveQuantity(quantity);
		return p;
	}
	
	@Path("/removeProduct")
	@DELETE
	@Produces(value = {MediaType.APPLICATION_JSON})
	public String removeProduct(@QueryParam("pid") int prodId){
		if(new OrderManager().removeProduct(prodId)) {
			return SUCCESS_RESULT;
		} else {
			return FAILURE_RESULT;
		}
	}
	
	@Path("/updateProduct")
	@PUT
	@Consumes(value = {MediaType.APPLICATION_JSON})
	@Produces(value = {MediaType.APPLICATION_JSON})
	public String updateProduct(Product p){
		if(new OrderManager().updateProduct(p)) {
			return SUCCESS_RESULT;
		} else {
			return FAILURE_RESULT;
		}
	}
	
	
	@Path("/addProduct")
	@POST
	@Consumes(value = {MediaType.APPLICATION_JSON})
	@Produces(value = {MediaType.APPLICATION_JSON})
	public String addProduct(Product p){
		if(new OrderManager().addProduct(p)) {
			return SUCCESS_RESULT;
		} else {
			return FAILURE_RESULT;
		}
	}

}
