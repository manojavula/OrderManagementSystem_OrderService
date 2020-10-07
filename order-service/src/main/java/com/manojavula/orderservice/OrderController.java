package com.manojavula.orderservice;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
	
	private final static Logger logger = LogManager.getLogger(OrderController.class);

	@Autowired
    private OrderService orderService;
    
    
    /**
     * List all the orders 
     * @param pageNo initial page no 1
     * @param pageSize initial page size 10
     * @return orders
     */
    @GetMapping("/orders")
    public ResponseEntity<?> listOrders(@RequestParam(name="pageNo", required=false, defaultValue = "1") Integer pageNo,
    		@RequestParam(name="pageSize", required = false, defaultValue = "10") Integer pageSize)  {
        try {
    	List<Order> patients = orderService.findAll(pageNo, pageSize);
        return new ResponseEntity<>(patients, HttpStatus.OK);
        }
        catch(Exception ex) {
        	logger.error(ex.getMessage());
        	return new ResponseEntity<>(new Error(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        	}
    }

    /**
     * Returns Order details based on the orderId
     * @param orderId orderId
     * @return Order
     */
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable(name="orderId") String orderId) {
        try {
    	Order order = orderService.getOrderById(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
        }
        catch(Exception ex) {
        	logger.error(ex.getMessage());
        	return new ResponseEntity<>(new Error(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        	}
    }

    /**
     * Create new order in the database
     * @param order order Object
     * @return
     */
    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody Orderbean order) {
        try {
        	    HttpStatus status = HttpStatus.NOT_FOUND;
	        	if(order != null && order.getOrderItems().length > 0) {
	        		
	        		Order orderReq = orderService.constructOrderRequest(order); 
	            	
	            	System.out.println(orderReq.toString());
	            	
	            	if( orderReq.getOrderItems().length > 0 ) {
	            		orderService.saveOrUpdateOrder(orderReq);
	            		status = HttpStatus.CREATED;
	            	} else {
	            		status = HttpStatus.NOT_FOUND; 
	            		logger.info("Order Item details not found.");
	            		throw new OrderNotFoundException("Order Item details not found."); 	            		 
	            	}
	        		
	        	}
        	
	        	return new ResponseEntity<>(status);
        }
        catch(Exception ex) {
        	logger.error(ex.getMessage());
        	return new ResponseEntity<>(new Error(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
   
}
