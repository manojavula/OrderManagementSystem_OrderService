package com.manojavula.orderservice;

import java.util.List;

public interface OrderService {
	
	/**
	 * List all the orders 
	 * @param pageNo PageNo
	 * @param pageSize total number of orders to be displayed in page
	 * @return Orders
	 */
    List<Order> findAll(Integer pageNo, Integer pageSize);

    /**
     * Get Order details by orderId
     * @param orderId 
     * @return Order
     */
    Order getOrderById(String orderId);

    /**
     * Save the new Order details or update the existing Order details.
     * @param patient Object
     */
    void saveOrUpdateOrder(Order order);
    
    /*
     * Return Order Request
     */
    Order constructOrderRequest(Orderbean order);
	

}
