package com.glovoapp.backender.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.glovoapp.backender.criteria.CriteriaService;
import com.glovoapp.backender.exception.NoCourierFoundException;
import com.glovoapp.backender.model.Courier;
import com.glovoapp.backender.model.Order;
import com.glovoapp.backender.model.OrderVM;
import com.glovoapp.backender.repository.CourierRepository;
import com.glovoapp.backender.repository.OrderRepository;
import com.glovoapp.backender.util.CourierUtil;
import com.glovoapp.backender.util.OrdersUtil;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OrderServiceTest {

	@MockBean
	OrderRepository orderRepository;

	@MockBean
	CourierRepository courierRepository;

	@MockBean
	CriteriaService criteriaService;

	@Autowired
	OrderService orderService;

	@Test
	void findAllWithResults() {
		when(orderRepository.findAll()).thenReturn(OrdersUtil.allOrders());

		List<OrderVM> orders = orderService.findAll();

		assertNotNull(orders);
		assertEquals(true, orders.size() > 0);
		assertEquals("order-1", orders.get(0).getId());

	}

	@Test
	void findAllWithNoResults() {
		when(orderRepository.findAll()).thenReturn(null);

		List<OrderVM> orders = orderService.findAll();

		assertNotNull(orders);
		assertEquals(true, orders.isEmpty());
	}

	@Test
	void findByCourierWithResults() throws NoCourierFoundException {
		String courierId = "c001";
		when(courierRepository.findById(courierId)).thenReturn(CourierUtil.getOne());
		when(orderRepository.findAll()).thenReturn(OrdersUtil.allOrders());

		List<Order> orders = orderRepository.findAll();
		Courier courier = courierRepository.findById(courierId);

		when(criteriaService.applyFilterCriteria(orders, courier)).thenReturn(OrdersUtil.getSome());

		List<OrderVM> ordersVM = orderService.findByCourier(courierId);

		assertNotNull(ordersVM);
		assertEquals(true, ordersVM.size() > 0);
		assertEquals("order-1", ordersVM.get(0).getId());
	}

	@Test
	void findByCourierWithNoCourier() {

		String courierId = "c001";
		when(courierRepository.findById(courierId)).thenReturn(null);
		when(orderRepository.findAll()).thenReturn(OrdersUtil.allOrders());
		List<Order> orders = orderRepository.findAll();
		Courier courier = courierRepository.findById(courierId);

		when(criteriaService.applyFilterCriteria(orders, courier)).thenReturn(OrdersUtil.getSome());

		assertThrows(NoCourierFoundException.class, () -> {
			orderService.findByCourier(courierId);
		});

	}

}
