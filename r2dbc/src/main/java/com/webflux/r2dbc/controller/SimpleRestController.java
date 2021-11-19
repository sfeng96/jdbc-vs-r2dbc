package com.webflux.r2dbc.controller;

import com.webflux.r2dbc.models.Order;
import com.webflux.r2dbc.repository.ReactiveOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "order")
@RequiredArgsConstructor
@Slf4j
public class SimpleRestController {

  private final ReactiveOrderRepository reactiveOrderRepository;

  @GetMapping
  public Flux<Order> hello() {
    return reactiveOrderRepository.findAll();
  }

  @GetMapping("/{orderId}/{productId}")
  public Flux<Order> getOrderById(@PathVariable String orderId, @PathVariable String productId) {
    return reactiveOrderRepository.findOrdersByOrderIdAndProductId(orderId, productId);
  }

  @PostMapping
  public Mono<Order> createOrder(@RequestBody Order order) {
    return reactiveOrderRepository
        .saveUser(order.getOrderId(), order.getProductId(), order.getAmount())
        .thenReturn(order);
  }
}
