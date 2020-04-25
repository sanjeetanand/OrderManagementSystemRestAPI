package com.psl.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "customer")
public class Customer {

	private int custId;
	private String custName,custPhone,custEmail,custAddress;
	
	public Customer (){
		super();
	}
	
	public Customer(int custId, String custName, String custPhone, String custEmail, String custAddress) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.custPhone = custPhone;
		this.custEmail = custEmail;
		this.custAddress = custAddress;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName + ", custPhone=" + custPhone + ", custEmail="
				+ custEmail + ", custAddress=" + custAddress + "]";
	}
	
}
