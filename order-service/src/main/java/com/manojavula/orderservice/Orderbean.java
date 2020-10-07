package com.manojavula.orderservice;

import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Orderbean implements Serializable {
    private static final long serialVersionUID = 1L;

	private String customerName;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date orderDate;
	
	private String shippingAddress;
	
	private String[] orderItems;
	
	private int total;	
		
}
