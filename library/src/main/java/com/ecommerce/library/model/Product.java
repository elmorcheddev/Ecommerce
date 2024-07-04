package com.ecommerce.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "product", uniqueConstraints = @UniqueConstraint(columnNames = {"name_prod", "image"}))
public class Product 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long id_prod;
    private String name_prod;
    private double castPrice_prod;
    private double salePrice_prod;
    private String description_prod;
    private int quantity;
    private boolean is_activate;
   private  boolean is_delete;
   @Lob
   @Column(columnDefinition = "MEDIUMBLOB")
   private String image;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cat", referencedColumnName = "id_cat")
    private Category category;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_marq", referencedColumnName = "id_marq")
    private MarqueProd marqueProd;
    
    
}
