package com.webflux.r2dbc.controller;

import com.webflux.r2dbc.dao.OrderDao;
import com.webflux.r2dbc.models.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "order")
@RequiredArgsConstructor
@Slf4j
public class SimpleRestController {

  private final OrderDao orderDao;

  @GetMapping
  public Flux<Order> hello() {
    return Flux.just(new Order());
  }

  @GetMapping("/{orderId}/{productId}")
  public Flux<Order> getOrderById(@PathVariable String orderId, @PathVariable String productId) {
    return Flux.just(new Order());
  }

  @PostMapping
  public Mono<Order> createOrder(@RequestBody Order order) {
    return orderDao.saveOrder(order).thenReturn(order);
  }
}
