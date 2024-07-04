package com.ecommerce.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.library.model.CustomUser;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, Long> {

	CustomUser findUserByEmail(String email);
	
}
