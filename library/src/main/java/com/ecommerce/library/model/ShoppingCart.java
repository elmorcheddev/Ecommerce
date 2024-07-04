package com.ecommerce.library.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
public class ShoppingCart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idshopping")
private Long id;
private int totlaitem;
private double totalPrice;
@OneToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "id_user", referencedColumnName = "id_user")
private CustomUser user;
@OneToMany(cascade = CascadeType.ALL,mappedBy = "cart")
private Set<CartItem> cartItem;
}
