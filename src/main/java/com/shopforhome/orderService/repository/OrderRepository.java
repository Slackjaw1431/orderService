package com.shopforhome.orderService.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopforhome.orderService.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

//	void saveOrder(Order order);
    Page<Order> findByUserId(String userName, Pageable pageable);	
	
}
