package com.ecommerce.library.repository;

import com.ecommerce.library.model.Category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	@Query("select c from Category c where c.is_active = true and c.is_delete= false")
	List<Category>getAllCategoriesActivated();
}
