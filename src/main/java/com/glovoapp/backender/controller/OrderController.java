package com.glovoapp.backender.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.glovoapp.backender.exception.NoCourierFoundException;
import com.glovoapp.backender.model.OrderVM;
import com.glovoapp.backender.service.OrderService;

@RestController
class OrderController {
	
	private final OrderService orderService;

    @Autowired
    OrderController(final OrderService orderService) {
    	this.orderService = orderService;
    }

    @GetMapping("/orders")
    List<OrderVM> orders() {
        return orderService.findAll();
    }
    
    @GetMapping("/orders/{courierId}")
    List<OrderVM> ordersByCourier(@PathVariable("courierId") final String courierId) throws NoCourierFoundException {
    	
    	return orderService.findByCourier(courierId);
    			
    }

}
