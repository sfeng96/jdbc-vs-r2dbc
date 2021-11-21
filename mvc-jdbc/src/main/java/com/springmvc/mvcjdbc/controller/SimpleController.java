package com.springmvc.mvcjdbc.controller;

import com.springmvc.mvcjdbc.dao.OrdersDao;

import com.springmvc.mvcjdbc.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "order")
@Slf4j
@RequiredArgsConstructor
public class SimpleController {

    private final OrdersDao ordersDao;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
         ordersDao.save(order);
        return order;
    }
}
