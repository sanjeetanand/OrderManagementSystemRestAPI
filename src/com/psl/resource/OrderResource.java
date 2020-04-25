package com.psl.resource;

import java.text.ParseException;
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

import com.psl.bean.Chart;
import com.psl.bean.Order;
import com.psl.bean.PopularProduct;
import com.psl.service.OrderManager;

@Path("/order")
public class OrderResource {
	
	private static final String SUCCESS_RESULT="{\"result\":\"success\"}";
	private static final String FAILURE_RESULT="{\"result\":\"fail\"}";

	@Path("/getAllOrder")
	@GET
	@Produces(value = {MediaType.APPLICATION_JSON})
	public List<Order> getAllOrder(){
		return new OrderManager().getAllOrder();
	}
	
	@Path("/addOrder")
	@POST
	@Produces(value = {MediaType.APPLICATION_JSON})
	@Consumes(value = {MediaType.APPLICATION_JSON})
	public String addOrder(Order order){
		if(new OrderManager().addOrder(order)) {
			return SUCCESS_RESULT;
		} else {
			return FAILURE_RESULT;
		}
	}
	
	@Path("/updateOrder")
	@PUT
	@Produces(value = {MediaType.APPLICATION_JSON})
	@Consumes(value = {MediaType.APPLICATION_JSON})
	public String updateOrder(Order order){
		if(new OrderManager().updateOrder(order)) {
			return SUCCESS_RESULT;
		} else {
			return FAILURE_RESULT;
		}
	}
	
	@Path("/removeOrder")
	@DELETE
	@Produces(value = {MediaType.APPLICATION_JSON})
	public String removeOrder(@QueryParam("oid") int orderId){
		if(new OrderManager().removeOrder(orderId)) {
			return SUCCESS_RESULT;
		} else {
			return FAILURE_RESULT;
		}
	}
	
	@Path("/getOrderById")
	@GET
	@Produces(value = {MediaType.APPLICATION_JSON})
	public Order getOrderById(@QueryParam("oid") int orderId){
		return new OrderManager().getOrderById(orderId);
	}
	
	@Path("/getOrderOfCust")
	@GET
	@Produces(value = {MediaType.APPLICATION_JSON})
	public Order getOrderOfCust(@QueryParam("cid") int custId){
		return new OrderManager().getOrderOfCust(custId);
	}
	
	@Path("/getOrderByStatus")
	@GET
	@Produces(value = {MediaType.APPLICATION_JSON})
	public List<Order> getOrderByStatus(@QueryParam("status") String status){
		return new OrderManager().getOrderByStatus(status);
	}
	
	@Path("/getOrderBeforeDate")
	@GET
	@Produces(value = {MediaType.APPLICATION_JSON})
	public List<Order> getOrderBeforeDate(@QueryParam("d") String date) throws ParseException{
		return new OrderManager().getOrderBeforeDate(date);
	}
	
	@Path("/getOrderAfterDate")
	@GET
	@Produces(value = {MediaType.APPLICATION_JSON})
	public List<Order> getOrderAfterDate(@QueryParam("d") String date) throws ParseException{
		return new OrderManager().getOrderAfterDate(date);
	}
	
	@Path("/getOrderBetweenDate")
	@GET
	@Produces(value = {MediaType.APPLICATION_JSON})
	public List<Order> getOrderBetweenDate(@QueryParam("sd") String startDate,@QueryParam("ed") String endDate) throws ParseException{
		return new OrderManager().getOrderBetweenDate(startDate,endDate);
	}
	
	@Path("/getOrderOnDate")
	@GET
	@Produces(value = {MediaType.APPLICATION_JSON})
	public List<Order> getOrderOnDate(@QueryParam("d") String date) throws ParseException{
		return new OrderManager().getOrderOnDate(date);
	}
	
	@Path("/getPopularProducts")
	@GET
	@Produces(value = {MediaType.APPLICATION_JSON})
	public List<PopularProduct> getPopularProducts(@QueryParam("month") int month, @QueryParam("num") int num) {
		List<PopularProduct> list = new OrderManager().getPopularProducts(month,num);
		return list;
	}
	
	@Path("/getChart")
	@GET
	@Produces(value = {MediaType.APPLICATION_JSON})
	public List<Chart> getChart() {
		return new OrderManager().getChart();
	}
	
	@Path("/getMonthChart")
	@GET
	@Produces(value = {MediaType.APPLICATION_JSON})
	public List<Chart> getMonthChart() {
		return new OrderManager().getChartByMonth(1);
	}
	
	@Path("/getQuarterChart")
	@GET
	@Produces(value = {MediaType.APPLICATION_JSON})
	public List<Chart> getQuarterChart() {
		return new OrderManager().getChartByMonth(3);
	}
	
	@Path("/getAnnualChart")
	@GET
	@Produces(value = {MediaType.APPLICATION_JSON})
	public List<Chart> getAnnualChart() {
		return new OrderManager().getChartByMonth(12);
	}
}
