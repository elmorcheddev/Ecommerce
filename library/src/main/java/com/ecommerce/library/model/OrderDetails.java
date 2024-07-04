package com.ecommerce.library.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetails {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_ordDetails;
	
	private int quantity;
	private double totalPrice,unitPrice;
	 @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	 @JoinColumn(name = "id_order", referencedColumnName = "id_order")
	  private Order order;
	 @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	    @JoinColumn(name = "id_product", referencedColumnName = "id_product")
	private Product product;
}
