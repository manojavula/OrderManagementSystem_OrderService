package com.manojavula.orderservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//@FeignClient(name="order-items-service", url="localhost:8200")
@FeignClient(name="order-items-service")
@RibbonClient(name="order-items-service")
public interface OrderItemsServiceProxy {

	@GetMapping("/orderitems/{productCode}")
	public OrderItems getOrderItemsByProductCode( @PathVariable("productCode") String productCode );
	
}
