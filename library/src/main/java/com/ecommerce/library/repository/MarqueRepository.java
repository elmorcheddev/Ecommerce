package com.ecommerce.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.library.model.Category;
import com.ecommerce.library.model.MarqueProd;
@Repository
public interface MarqueRepository extends JpaRepository<MarqueProd, Long> {
	@Query("select m from MarqueProd m where m.isactive = true and m.isdelete= false")
	List<MarqueProd>getAllMarqueProdsActivated();
}
