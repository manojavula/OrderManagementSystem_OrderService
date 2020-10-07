package com.manojavula.orderservice;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name = "\"Order\"")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String customerName;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date orderDate;
	
	private String shippingAddress;
	
	/*@OneToMany(targetEntity = OrderItems.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "order_fk", referencedColumnName = "id")*/
	private String[] orderItems;
	
	private int total;
		
		
}
