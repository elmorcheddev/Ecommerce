package com.ecommerce.library.repository;

import com.ecommerce.library.dto.ProductDto;
import com.ecommerce.library.model.Category;
import com.ecommerce.library.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("select p from Product p where p.is_activate=true and p.is_delete=false")
	List<Product>allproductactive();
	@Query(value = "select * from ecommerce.product p inner join ecommerce.category_product c on c.id_cat = p.id_cat where p.id_cat = ?1", nativeQuery = true)
	List<Product>allproductBycategory(Long id_cat);
	@Query(value = "select * from ecommerce.product p inner join ecommerce.marque_prod m on m.id_marq = p.id_marq where p.id_marq = ?1", nativeQuery = true)
	List<Product>allproductByBrand(Long id_cat);
}
