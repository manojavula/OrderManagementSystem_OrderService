package com.manojavula.orderservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	private OrderItemsServiceProxy proxy;
	

	@Override
	public List<Order> findAll(Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}

	@Override
	public Order getOrderById(String orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdateOrder(Order order) {
		// TODO Auto-generated method stub
		orderRepository.saveAndFlush(order);
	}
	
	@Override
	public Order constructOrderRequest(Orderbean order) {
	    	
	    	Order _order = new Order();
	    	_order.setCustomerName(order.getCustomerName());
	    	_order.setOrderDate(order.getOrderDate());
	    	_order.setShippingAddress(order.getShippingAddress());
	    	_order.setTotal(order.getTotal());
	    	
	    	OrderItems orderItem = new OrderItems();
	    	List<String> orderItemsList = new ArrayList<>();
	    	for ( String strOrder: order.getOrderItems() )  {
	    		orderItem = proxy.getOrderItemsByProductCode( strOrder ); 
	    		if( orderItem.getProductCode() != null && orderItem.getQuantity() != 0) {
	    			orderItemsList.add(strOrder);
	        	} 
	    	}
	    	
	    	String[] orderItems = Arrays.copyOf(orderItemsList.toArray(), orderItemsList.size(),String[].class);
	    	System.out.println("orderItems============>"+orderItems);
	    	
	    	_order.setOrderItems(orderItems);
	    	
	    	
	    	return _order;
	   }

}
