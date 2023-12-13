package com.shopforhome.orderService.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@JsonDeserialize(using = OrderDeserializer.class)
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "date_created")
	private String dateCreated;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "total")
	private double total;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private Set<OrderItem> orderItems = new HashSet<>();

	public void add(OrderItem item) {

		if (item != null) {
			if (orderItems == null) {
				orderItems = new HashSet<>();
			}

			orderItems.add(item);
			item.setOrder(this);
		}
	}

}
