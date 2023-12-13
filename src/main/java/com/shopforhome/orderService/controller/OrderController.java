package com.shopforhome.orderService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopforhome.orderService.entity.Order;
import com.shopforhome.orderService.service.OrderService;

@CrossOrigin
@RestController
public class OrderController {

	@Autowired
	OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping({"order/Hello"})
	public String hello() {
		return "Hello from orders";
	}

	@PostMapping({"/order"})
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
//	public void createOrder(@RequestBody Order order) {
		Order createdOrder = orderService.createOrder(order);
//		orderService.createOrder(order);
		return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
	}

	@GetMapping({"/byUser/{userId}"})
	public ResponseEntity<Page<Order>> getOrdersByUserId(@PathVariable String userId,
			@PageableDefault(size = 5, sort = "dateCreated", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Order> orders = orderService.getOrdersByUserId(userId, pageable);
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
}
