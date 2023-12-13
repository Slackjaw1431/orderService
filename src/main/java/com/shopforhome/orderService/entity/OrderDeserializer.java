package com.shopforhome.orderService.entity;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

@Component
public class OrderDeserializer extends StdDeserializer<Order> {

	public OrderDeserializer() {
		this(null);
	}

	public OrderDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public Order deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
		JsonNode node = parser.getCodec().readTree(parser);

		String userId = node.get("userId").asText();
		double total = node.get("total").asDouble();
		String dateCreated = node.get("dateCreated").asText();

		Set<OrderItem> items = new HashSet<>();
		Order order = new Order();
		order.setUserId(userId);
		order.setTotal(total);
		
		JsonNode productsNode = node.get("products");
		if (productsNode != null && productsNode.isArray()) {
			for (JsonNode productNode : productsNode) {
				Long id = productNode.get("id").asLong();
				int quantity = productNode.get("quantity").asInt();
				String name = productNode.get("name").asText();
				BigDecimal unitPrice = productNode.get("unitPrice").decimalValue();
//				System.out.println(id + quantity + name + unitPrice);
				OrderItem item = new OrderItem();
//				item.setOrderItemId(id);
				item.setProductId(id);
				item.setQuantity(quantity);
				item.setName(name);
				item.setUnitPrice(unitPrice);
				item.setOrder(order);
//				System.out.println(item.getOrderItemId());
//				System.out.println(item.getName());

				items.add(item);
			}
		}
		
		order.setDateCreated(dateCreated);
		order.setOrderItems(items);
//		System.out.println(order.getOrderId());

		return order;
	}

}
