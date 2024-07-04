package com.ecommerce.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.library.model.OrderDetails;

@Repository
public interface OrederDetailsRepository extends JpaRepository<OrderDetails	, Long> {

}
