package com.ecommerce.library.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="marque_prod " , uniqueConstraints = @UniqueConstraint(columnNames = "name_marque"))
public class MarqueProd {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "id_marq")
	private Long id_marq;
	private String name_marque;
	@Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
	private boolean isactive;
	private boolean isdelete;
	public MarqueProd(String name_marque) {
		this.name_marque = name_marque;
		this.isactive = true;
		this.isdelete = false;
	}
	
}
