package com.psl.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import com.psl.bean.Category;
import com.psl.bean.Chart;
import com.psl.bean.Order;
import com.psl.bean.PopularProduct;
import com.psl.bean.Product;
import com.psl.bean.Status;
import com.psl.util.DBManager;

public class OrderDao {

	private DBManager dbm = null;
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;


	//get all orders available in the database
	public List<Order> getAllOrder(){
		List<Order> orderList = new ArrayList<Order>();
		dbm = new DBManager();
		try {
			if(con == null) {
				con = dbm.getConnection();
			}

			String query = "SELECT * from order_tb";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while(rs.next()) {
				int orderId = rs.getInt("orderId");
				int custId = rs.getInt("custId");
				Date orderDate = rs.getDate("orderDate");
				Status orderStatus = Status.valueOf(rs.getString("orderStatus").toUpperCase());
				double orderCost = rs.getDouble("orderCost");

				Map<Product, Integer> orderProduct = new HashMap<Product, Integer>();
				Scanner sc = new Scanner(rs.getString("orderProduct"));
				sc.useDelimiter(",");
				while(sc.hasNext()) {
					Scanner c = new Scanner(sc.next());
					c.useDelimiter(":");

					Product p = new ProductDao().getProductByName(c.next());
					int prodId = p.getProdId();
					String prodName = p.getProdName();
					Category prodCategory = p.getCategory();
					String prodDesc = p.getProdDesc();
					double prodPrice = p.getProdPrice();
					int prodQunatity = p.getProdQuantity();

					int quantity = Integer.parseInt(c.next());

					orderProduct.put(new Product(prodId,prodName,prodCategory,prodDesc,prodPrice,prodQunatity), quantity);
					c.close();
				}
				sc.close();

				orderList.add(new Order(orderId,custId,orderDate,orderStatus,orderCost,orderProduct));
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				ps.close();rs.close();dbm.closeConnection();
				ps = null;rs = null;con = null;
			} catch(SQLException e) {
				System.out.println(e);
			}
		}
		return orderList;
	}

	//add new order to the database
	public boolean addOrder(Order order){
		boolean flag = false;
		dbm = new DBManager();
		try {
			if(con == null) {
				con = dbm.getConnection();
			}

			String query = "INSERT INTO order_tb(custId,orderDate,orderStatus,orderCost,orderProduct)"
					+ "VALUES(?,?,?,?,?)";
			ps = con.prepareStatement(query);
			ps.setInt(1,order.getCustId());
			ps.setDate(2,new Date(new java.util.Date().getTime()));
			ps.setString(3,Status.SUBMITTED.toString());

			Map<Product, Integer> map = order.getOrderProduct();
			ps.setDouble(4,new OrderDao().calCost(map));

			//products are stored like prodName : quantity
			StringBuffer orderProduct = new StringBuffer();
			for(Product p : map.keySet()) {
				orderProduct.append(p.getProdName()+":"+map.get(p)+",");
			}
			orderProduct.replace(orderProduct.lastIndexOf(","), orderProduct.length(), "");
			ps.setString(5,orderProduct.toString());

			if(ps.executeUpdate() > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				ps.close();dbm.closeConnection();
				ps = null;con = null;
			} catch(SQLException e) {
				System.out.println(e);
			}
		}
		return flag;
	}

	//calculate the cost of orders
	public double calCost(Map<Product, Integer> map) {
		double totalCost = 0;
		for(Product s : map.keySet()) {
			totalCost += s.getProdPrice() * map.get(s);
		}
		return totalCost;
	}

	//update order in the database
	public boolean updateOrder(Order order){
		boolean flag = false;
		dbm = new DBManager();
		try {
			if(con == null) {
				con = dbm.getConnection();
			}

			String query = "UPDATE order_tb SET orderStatus=?,orderDate=?,orderCost=?,orderProduct=? WHERE orderID=?";
			ps = con.prepareStatement(query);

			Map<Product, Integer> map = order.getOrderProduct();
			StringBuffer orderProduct = new StringBuffer();
			for(Product p : map.keySet()) {
				orderProduct.append(p.getProdName()+":"+map.get(p)+",");
			}
			orderProduct.replace(orderProduct.lastIndexOf(","), orderProduct.length(), "");

			ps.setString(1,order.getOrderStatus().toString());
			ps.setDate(2,new Date(new java.util.Date().getTime()));
			ps.setDouble(3, new OrderDao().calCost(map));
			ps.setString(4,orderProduct.toString());
			ps.setInt(5, order.getOrderId());

			if(ps.executeUpdate() > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				ps.close();dbm.closeConnection();
				ps = null;con = null;
			} catch(SQLException e) {
				System.out.println(e);
			}
		}
		return flag;
	}


	//remove order in the database
	public boolean removeOrder(int orderId){
		boolean flag = false;
		dbm = new DBManager();
		try {
			if(con == null) {
				con = dbm.getConnection();
			}

			String query = "DELETE FROM order_tb WHERE orderID=?";
			ps = con.prepareStatement(query);

			ps.setInt(1,orderId);

			if(ps.executeUpdate() > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				ps.close();dbm.closeConnection();
				ps = null;con = null;
			} catch(SQLException e) {
				System.out.println(e);
			}
		}
		return flag;
	}


	//search and return Order with given custId
	public Order getOrderOfCust(int custId){

		List<Order> orderList = new OrderDao().getAllOrder();
		if(orderList != null && !orderList.isEmpty()) {
			for(Order p : orderList) {
				if(p.getCustId() == custId) {
					return p;					
				}
			}
		}
		return null;
	}


	//search and return Order with given orderId
	public Order getOrderById(int orderId){

		List<Order> orderList = new OrderDao().getAllOrder();
		if(orderList != null && !orderList.isEmpty()) {
			for(Order p : orderList) {
				if(p.getOrderId() == orderId) {
					return p;					
				}
			}
		}
		return null;
	}


	//search and return Order with given orderStatus
	public List<Order> getOrderByStatus(String status){

		List<Order> list = new ArrayList<Order>();
		List<Order> orderList = new OrderDao().getAllOrder();
		if(orderList != null && !orderList.isEmpty()) {
			for(Order p : orderList) {
				if(p.getOrderStatus() == Status.valueOf(status.toUpperCase())) {
					list.add(p);					
				}
			}
		}
		return list;
	}

	//search and return Orders done before given date
	public List<Order> getOrderBeforeDate(String date) throws ParseException{

		List<Order> orderList = new OrderDao().getAllOrder();
		List<Order> list = new ArrayList<Order>();
		if(orderList != null && !orderList.isEmpty()) {
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			sf.setLenient(false);
			Date d = new Date(sf.parse(date).getTime());
			for(Order p : orderList) {
				if(p.getOrderDate().before(d)) {
					list.add(p);				
				}
			}
		}
		return list;
	}

	//search and return Orders done after given date
	public List<Order> getOrderAfterDate(String date) throws ParseException{

		List<Order> orderList = new OrderDao().getAllOrder();
		List<Order> list = new ArrayList<Order>();
		if(orderList != null && !orderList.isEmpty()) {
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			sf.setLenient(false);
			Date d = new Date(sf.parse(date).getTime());
			for(Order p : orderList) {
				if(p.getOrderDate().after(d)) {
					list.add(p);				
				}
			}
		}
		return list;
	}


	//search and return Orders done between given date
	public List<Order> getOrderBetweenDate(String startDate, String endDate) throws ParseException{

		List<Order> orderList = new OrderDao().getAllOrder();
		List<Order> list = new ArrayList<Order>();
		if(orderList != null && !orderList.isEmpty()) {
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			sf.setLenient(false);
			Date sd = new Date(sf.parse(startDate).getTime());
			Date ed = new Date(sf.parse(endDate).getTime());
			for(Order p : orderList) {
				if(p.getOrderDate().after(sd) && p.getOrderDate().before(ed)) {
					list.add(p);				
				}
			}
		}
		return list;
	}


	//search and return Orders done on given date
	public List<Order> getOrderOnDate(String date) throws ParseException{

		List<Order> orderList = new OrderDao().getAllOrder();
		List<Order> list = new ArrayList<Order>();
		if(orderList != null && !orderList.isEmpty()) {
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			sf.setLenient(false);
			Date d = new Date(sf.parse(date).getTime());
			for(Order p : orderList) {
				if(p.getOrderDate().compareTo(d) == 0) {
					list.add(p);				
				}
			}
		}
		return list;
	}

	//search and return popular products in sorted order in given duration(months)

	//returns a map of product and no of customer(popular Products)
	public List<PopularProduct> getPopularProducts(int month, int num) {

		List<Order> orderList = new OrderDao().getAllOrder();

		class ProductCountComparator implements Comparator<Entry<Product, Integer>> {

			@Override
			public int compare(Entry<Product, Integer> p1, Entry<Product, Integer> p2) {
				return p2.getValue()-p1.getValue();
			}

		}

		List<PopularProduct> list = new LinkedList<PopularProduct>();

		if(orderList != null && !orderList.isEmpty()) {

			Map<Product, Integer> map = new HashMap<Product, Integer>();

			java.util.Date today = new java.util.Date();
			Calendar c = Calendar.getInstance();
			c.clear();
			c.setTime(today);
			c.add(Calendar.MONTH, -month);
			java.util.Date sDate = c.getTime();

			Date ed = new Date(today.getTime());
			Date sd = new Date(sDate.getTime());
			for(Order o : orderList) {
				if(o.getOrderDate().after(sd) && o.getOrderDate().before(ed)) {
					for(Product p : o.getOrderProduct().keySet()) {
						if(map.containsKey(p)) {
							map.put(p, map.get(p)+1);
						} else {
							map.put(p, 1);
						}
					}
				}
			}

			Set<Entry<Product, Integer>> entry = map.entrySet();
			List<Entry<Product, Integer>> l = new ArrayList<Entry<Product,Integer>>(entry);
			Collections.sort(l, new ProductCountComparator());

			if(num == 0) {
				for(Entry<Product, Integer> e : l) {
					list.add(new PopularProduct(e.getKey(),e.getValue()));
				}
			} else {
				for(Entry<Product, Integer> e : l) {
					if(num > 0) {
						list.add(new PopularProduct(e.getKey(),e.getValue()));
						num--;
					} else {
						break;
					}
				}
			}

		}

		return list;
	}


	//returns category wise order products details along with their sales.
	public List<Chart> getChart() {

		List<Order> orderList = new OrderDao().getAllOrder();
		List<Chart> sales = new ArrayList<Chart>();
		Map<Category, Map<Product, Integer>> map = new HashMap<Category, Map<Product, Integer>>();

		if(orderList != null && !orderList.isEmpty()) {
			for(Order o : orderList) {
				for(Product p : o.getOrderProduct().keySet()) {
					if(map.containsKey(p.getCategory())) {
						if(map.get(p.getCategory()).containsKey(p)) {
							map.get(p.getCategory()).put(p, map.get(p.getCategory()).get(p) + o.getOrderProduct().get(p));
						} else {
							map.get(p.getCategory()).put(p,o.getOrderProduct().get(p));
						}
					} else {
						map.put(p.getCategory(),new HashMap<Product, Integer>());
						map.get(p.getCategory()).put(p,o.getOrderProduct().get(p));
					}
				}
			}
		}

		for(Category c : map.keySet()) {
			sales.add(new Chart(c,map.get(c)));
		}

		return sales;
	}



	//returns category wise order products details along with their sales according to duration in months.
	public List<Chart> getChartByMonth(int month) {

		List<Order> orderList = new OrderDao().getAllOrder();
		List<Chart> sales = new ArrayList<Chart>();
		Map<Category, Map<Product, Integer>> map = new HashMap<Category, Map<Product, Integer>>();

		if(orderList != null && !orderList.isEmpty()) {

			java.util.Date today = new java.util.Date();
			Calendar c = Calendar.getInstance();
			c.clear();
			c.setTime(today);
			c.add(Calendar.MONTH, -month);
			java.util.Date sDate = c.getTime();

			Date ed = new Date(today.getTime());
			Date sd = new Date(sDate.getTime());

			for(Order o : orderList) {
				if(o.getOrderDate().after(sd) && o.getOrderDate().before(ed)) {
					for(Product p : o.getOrderProduct().keySet()) {
						if(map.containsKey(p.getCategory())) {
							if(map.get(p.getCategory()).containsKey(p)) {
								map.get(p.getCategory()).put(p, map.get(p.getCategory()).get(p) + o.getOrderProduct().get(p));
							} else {
								map.get(p.getCategory()).put(p,o.getOrderProduct().get(p));
							}
						} else {
							map.put(p.getCategory(),new HashMap<Product, Integer>());
							map.get(p.getCategory()).put(p,o.getOrderProduct().get(p));
						}
					}
				}
			}
		}

		for(Category c : map.keySet()) {
			sales.add(new Chart(c,map.get(c)));
		}

		return sales;
	}

}
