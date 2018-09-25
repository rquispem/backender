package com.glovoapp.backender.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.glovoapp.backender.model.Order;
import com.glovoapp.backender.repository.OrderRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class OrdersUtil {

	private static final String ORDERS_FILE = "/data/orders.json";
	private static final List<Order> orders;

	static {
		try (Reader reader = new InputStreamReader(OrderRepository.class.getResourceAsStream(ORDERS_FILE))) {
			Type type = new TypeToken<List<Order>>() {
			}.getType();
			orders = new Gson().fromJson(reader, type);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static List<Order> allOrders() {
		return new ArrayList<>(orders);
	}

	public static List<Order> getSome() {
		return  new ArrayList<>(orders);
	}
}
