package com.ecommerce.library.dto;

import com.ecommerce.library.model.Category;
import com.ecommerce.library.model.MarqueProd;

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
public class ProductDto {

    private Long id_prod;
    private String name_prod;
    
    private double castPrice_prod;
    private double salePrice_prod;
    private String description_prod;
    private int quantity;
    private Category category;
    private MarqueProd marqueProd;
    private String image;
    private boolean is_activate;
    private  boolean is_delete;
	

}