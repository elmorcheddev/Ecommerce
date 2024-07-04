package com.ecommerce.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.library.model.CartItem;

@Repository
public interface CartitemRepository extends JpaRepository<CartItem, Long> {

	
}
