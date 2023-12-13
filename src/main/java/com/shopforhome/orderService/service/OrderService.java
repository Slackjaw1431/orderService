package com.shopforhome.orderService.service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shopforhome.orderService.entity.Order;
import com.shopforhome.orderService.repository.OrderRepository;

@Service
@Transactional
public class OrderService {

	@Autowired
	EntityManager em;
	
	@Autowired
	OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public Page<Order> getOrdersByUserId(String userId, Pageable pageable) {
		return orderRepository.findByUserId(userId, pageable);
	}

	@Transactional
	public Order createOrder(Order order) {
		em.persist(order);
		return orderRepository.save(order);
	}

}
