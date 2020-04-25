package com.psl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.psl.bean.Category;
import com.psl.bean.Product;
import com.psl.util.DBManager;

public class ProductDao {

	private DBManager dbm = null;
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;


	//get all products available in the database
	public List<Product> getAllProduct(){
		List<Product> prodList = new ArrayList<Product>();
		dbm = new DBManager();
		try {
			if(con == null) {
				con = dbm.getConnection();
			}

			String query = "SELECT * from product_tb";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while(rs.next()) {
				int prodId = rs.getInt("prodId");
				String prodName = rs.getString("prodName");
				String prodDesc = rs.getString("prodDesc");
				Category category = Category.valueOf(rs.getString("prodCategory").toUpperCase());
				double prodPrice = rs.getDouble("prodPrice");
				int prodQuantity = rs.getInt("prodQuantity");

				prodList.add(new Product(prodId,prodName,category,prodDesc,prodPrice,prodQuantity));
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
		return prodList;
	}


	//search and return the product with given product id in the Product List
	public Product getProductById(int id){

		List<Product> prodList = new ProductDao().getAllProduct();
		if(prodList != null && !prodList.isEmpty()) {
			for(Product p : prodList) {
				if(p.getProdId() == id) {
					return p;					
				}
			}
		}
		return null;
	}

	//search and return the product with given product name in the Product List
	public Product getProductByName(String prodName){

		List<Product> prodList = new ProductDao().getAllProduct();
		if(prodList != null && !prodList.isEmpty()) {
			for(Product p : prodList) {
				if(p.getProdName().equalsIgnoreCase(prodName)) {
					return p;					
				}
			}
		}
		return null;
	}

	//search and return the product with given product category in the Product List
	public List<Product> getProductByCategory(String category){

		List<Product> prodList = new ProductDao().getAllProduct();
		List<Product> list = new ArrayList<Product>();
		if(prodList != null && !prodList.isEmpty()) {
			for(Product p : prodList) {
				if(p.getCategory().equals(Category.valueOf(category.toUpperCase()))) {
					list.add(p);					
				}
			}
		}
		return list;
	}

	//search and return the list of products from the Product List with quantity below the given quantity
	public List<Product> getProductBelowQuantity(int quantity){

		List<Product> prodList = new ProductDao().getAllProduct();
		List<Product> list = new ArrayList<Product>();
		if(prodList != null && !prodList.isEmpty()) {
			for(Product p : prodList) {
				if(p.getProdQuantity() <= quantity) {
					list.add(p);					
				}
			}
		}
		return list;
	}

	//search and return the list of products from the Product List with quantity above the given quantity
	public List<Product> getProductAboveQuantity(int quantity){

		List<Product> prodList = new ProductDao().getAllProduct();
		List<Product> list = new ArrayList<Product>();
		if(prodList != null && !prodList.isEmpty()) {
			for(Product p : prodList) {
				if(p.getProdQuantity() >= quantity) {
					list.add(p);					
				}
			}
		}
		return list;
	}

	//remove a product from the database the database
	public boolean removeProduct(int prodId){
		boolean flag = false;
		dbm = new DBManager();
		try {
			if(con == null) {
				con = dbm.getConnection();
			}

			String query = "DELETE FROM product_tb where prodId = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1,prodId);

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

	//update existing product to the database
	public boolean updateProduct(Product p){
		boolean flag = false;
		dbm = new DBManager();
		try {
			if(con == null) {
				con = dbm.getConnection();
			}

			String query = "UPDATE product_tb SET prodName=?,prodCategory=?,prodDesc=?,prodPrice=?,prodQuantity=? WHERE prodId=?";
			ps = con.prepareStatement(query);
			ps.setString(1,p.getProdName());
			ps.setString(2,p.getCategory().toString());
			ps.setString(3,p.getProdDesc());
			ps.setDouble(4,p.getProdPrice());
			ps.setInt(5,p.getProdQuantity());
			ps.setInt(6,p.getProdId());

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

	//add new product to the database
	public boolean addProduct(Product p){
		boolean flag = false;
		dbm = new DBManager();
		try {
			if(con == null) {
				con = dbm.getConnection();
			}

			String query = "INSERT INTO product_tb(prodName,prodCategory,prodDesc,prodPrice,prodQuantity)"
					+ "VALUES(?,?,?,?,?)";
			ps = con.prepareStatement(query);
			ps.setString(1,p.getProdName());
			ps.setString(2,p.getCategory().toString());
			ps.setString(3,p.getProdDesc());
			ps.setDouble(4,p.getProdPrice());
			ps.setInt(5,p.getProdQuantity());

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
	
	public static void main(String[] args) {
		System.out.println(new ProductDao().getAllProduct());
	}
}
