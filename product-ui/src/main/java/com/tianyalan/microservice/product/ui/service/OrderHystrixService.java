package com.tianyalan.microservice.product.ui.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tianyalan.microservice.product.ui.domain.Order;

@Service
public class OrderHystrixService {

	@Autowired
	OrderService orderService;
	
	@HystrixCommand(fallbackMethod = "fallbackSave") 
	public List<Order> save(Order order) {
		return orderService.save(order);
	}
	
	public List<Order> fallbackSave(Order order){ 
		List<Order> list = new ArrayList<>();
		Order o = new Order();
		o.setItems(order.getItems()+": Order service failed!");
		list.add(o);
		return list;
	}
}
