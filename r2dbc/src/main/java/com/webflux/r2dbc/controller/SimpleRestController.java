package com.webflux.r2dbc.controller;

import com.webflux.r2dbc.models.Order;
import com.webflux.r2dbc.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "order")
@RequiredArgsConstructor
@Slf4j
public class SimpleRestController {

  private final OrderService orderService;

  @PostMapping
  public Mono<Order> createOrder(@RequestBody Order order) {
    return orderService.saveOrder(order).thenReturn(order);
  }
}
