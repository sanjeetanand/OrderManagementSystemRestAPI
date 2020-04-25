package com.psl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.psl.bean.Customer;
import com.psl.util.DBManager;

public class CustomerDao {

	private DBManager dbm = null;
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public List<Customer> getAllCustomer() {

		List<Customer> custList = new ArrayList<Customer>();
		dbm = new DBManager();
		try {
			if(con == null) {
				con = dbm.getConnection();
			}

			String query = "SELECT * from customer_tb";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while(rs.next()) {
				int custId = rs.getInt("custId");
				String custName = rs.getString("custName");
				String custPhone = rs.getString("custPhone");
				String custEmail = rs.getString("custEmail");
				String custAddress = rs.getString("custAddress");

				custList.add(new Customer(custId,custName,custPhone,custEmail,custAddress));
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
		return custList;
	}

	public Customer getCustomer(int id) {

		Customer cust = null;
		dbm = new DBManager();
		try {
			if(con == null) {
				con = dbm.getConnection();
			}

			String query = "SELECT * from customer_tb where custId=?";
			ps = con.prepareStatement(query);
			ps.setInt(1,id);
			rs = ps.executeQuery();

			while(rs.next()) {
				int custId = rs.getInt("custId");
				String custName = rs.getString("custName");
				String custPhone = rs.getString("custPhone");
				String custEmail = rs.getString("custEmail");
				String custAddress = rs.getString("custAddress");

				cust = new Customer(custId,custName,custPhone,custEmail,custAddress);
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				ps.close();
				rs.close();
				dbm.closeConnection();
				ps = null;rs = null;con = null;
			} catch(SQLException e) {
				System.out.println(e);
			}
		}
		return cust;
	}

	public boolean addCustomer(Customer cust) {
		boolean flag = false;
		dbm = new DBManager();
		try {
			if(con == null) {
				con = dbm.getConnection();
			}

			String query = "INSERT INTO customer_tb(custName,custPhone,custEmail,custAddress) VALUES (?,?,?,?)";
			ps = con.prepareStatement(query);
			ps.setString(1,cust.getCustName());
			ps.setString(2,cust.getCustPhone());
			ps.setString(3,cust.getCustEmail());
			ps.setString(4,cust.getCustAddress());

			if(ps.executeUpdate() > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				ps.close();dbm.closeConnection();
				ps = null;rs = null;con = null;
			} catch(SQLException e) {
				System.out.println(e);
			}
		}
		return flag;
	}

	public boolean removeCustomer(int cust) {
		boolean flag = false;
		dbm = new DBManager();
		try {
			if(con == null) {
				con = dbm.getConnection();
			}

			String query = "DELETE FROM customer_tb WHERE custId = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1,cust);

			if(ps.executeUpdate() > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				ps.close();
				dbm.closeConnection();
				ps = null;rs = null;con = null;
			} catch(SQLException e) {
				System.out.println(e);
			}
		}
		return flag;
	}

	public boolean updateCustomer(Customer cust) {
		boolean flag = false;
		dbm = new DBManager();
		try {
			if(con == null) {
				con = dbm.getConnection();
			}

			String query = "UPDATE customer_tb SET custName=?,custPhone=?,custEmail=?,custAddress=? where custId=?";
			ps = con.prepareStatement(query);
			ps.setString(1,cust.getCustName());
			ps.setString(2,cust.getCustPhone());
			ps.setString(3,cust.getCustEmail());
			ps.setString(4,cust.getCustAddress());
			ps.setInt(5,cust.getCustId());

			if(ps.executeUpdate() > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				ps.close();dbm.closeConnection();
				ps = null;rs = null;con = null;
			} catch(SQLException e) {
				System.out.println(e);
			}
		}
		return flag;
	}
	
	public static void main(String[] args) {
		System.out.println(new CustomerDao().addCustomer(new Customer(4,"Sanjeet","8651805210","neha@gmail.com","Patna, Bihar")));
		
	}

}
