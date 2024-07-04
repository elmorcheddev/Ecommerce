package com.ecommerce.library.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames =  {"email","photo","phone"}))
public class CustomUser {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_user;
private String 		firstname
					,lastname
					,email
					,password
					,datenaissance
					
					,adresse
					,phone;

@OneToMany(mappedBy = "user")
private List<Order> orders;

@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id_user"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
private Collection<Role> role;
@Lob
@Column(columnDefinition = "MEDIUMBLOB")
private String photo;
@OneToOne(mappedBy = "user")
private ShoppingCart shoppingCart;
}
