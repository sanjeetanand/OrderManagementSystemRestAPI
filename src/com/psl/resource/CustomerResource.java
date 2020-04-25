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
import com.psl.bean.Customer;
import com.psl.service.OrderManager;


@Path("/cust")
public class CustomerResource {

	private static final String SUCCESS_RESULT="{\"result\":\"success\"}";
	private static final String FAILURE_RESULT="{\"result\":\"fail\"}";

	@Path("/getAllCustomer")
	@GET
	@Produces(value = {MediaType.APPLICATION_JSON})
	public List<Customer> getAllCustomer() {
		List<Customer> custList = new OrderManager().getAllCustomer();
		return custList;
	}

	@Path("/getCustomer")
	@GET
	@Produces(value = {MediaType.APPLICATION_JSON})
	public Customer getCustomer(@QueryParam("custId") int custId) {
		Customer cust = new OrderManager().getCustomer(custId);
		return cust;
	}

	@Path("/addCustomer")
	@POST
	@Consumes(value = {MediaType.APPLICATION_JSON})
	@Produces(value = {MediaType.APPLICATION_JSON})
	public String addCustomer(Customer customer) {
		if(new OrderManager().addCustomer(customer)) {
			return SUCCESS_RESULT;
		} else {
			return FAILURE_RESULT;
		}
	}

	@Path("/removeCustomer")
	@DELETE
	@Produces(value = {MediaType.APPLICATION_JSON})
	public String removeCustomer(@QueryParam("custId") int custId) {
		if(new OrderManager().removeCustomer(custId)) {
			return SUCCESS_RESULT;
		} else {
			return FAILURE_RESULT;
		}
	}

	@Path("/updateCustomer")
	@PUT
	@Consumes(value = {MediaType.APPLICATION_JSON})
	@Produces(value = {MediaType.APPLICATION_JSON})
	public String updateCustomer(Customer customer) {
		if(new OrderManager().updateCustomer(customer)) {
			return SUCCESS_RESULT;
		} else {
			return FAILURE_RESULT;
		}
	}

}
