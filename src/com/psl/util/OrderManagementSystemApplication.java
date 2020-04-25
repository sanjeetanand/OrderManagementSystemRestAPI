package com.psl.util;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.psl.resource.CustomerResource;
import com.psl.resource.OrderResource;
import com.psl.resource.ProductResource;

@ApplicationPath("/")
public class OrderManagementSystemApplication extends Application{

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes;
		classes = new HashSet<Class<?>>();
		classes.add(CustomerResource.class);
		classes.add(ProductResource.class);
		classes.add(OrderResource.class);
		return classes;
	}
}
