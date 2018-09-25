package com.glovoapp.backender.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.glovoapp.backender.criteria.CriteriaService;
import com.glovoapp.backender.exception.NoCourierFoundException;
import com.glovoapp.backender.model.Courier;
import com.glovoapp.backender.model.Order;
import com.glovoapp.backender.model.OrderVM;
import com.glovoapp.backender.repository.CourierRepository;
import com.glovoapp.backender.repository.OrderRepository;

@Service
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final CourierRepository courierRepository;
	private final CriteriaService criteriaService;

	
	public OrderService(final OrderRepository orderRepository, 
						final CourierRepository courierRepository,
						final CriteriaService criteriaService) {
		
		this.orderRepository = orderRepository;
		this.courierRepository = courierRepository;
		this.criteriaService = criteriaService;
	}

	public List<OrderVM> findAll() {
		return retrieveAllOrdersVM(orderRepository.findAll());
	}
	
	public List<OrderVM> findByCourier(String courierId) throws NoCourierFoundException {
		Courier courier = courierRepository.findById(courierId);
		List<Order> orders = orderRepository.findAll();
		
		if (null == courier) {
			throw new NoCourierFoundException("There is no courier");
		}
		
		orders = criteriaService.applyFilterCriteria(orders, courier);
		
		return retrieveAllOrdersVM(orders);
	}

	private List<OrderVM> retrieveAllOrdersVM(List<Order> orders) {
		return orders == null ? new ArrayList<>() :  orders
        .stream()
        .map(order -> new OrderVM(order.getId(), order.getDescription()))
        .collect(Collectors.toList());
	}
	
	
}
