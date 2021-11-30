package com.webflux.r2dbc.service;

import com.webflux.r2dbc.models.Order;
import com.webflux.r2dbc.repository.ReactiveOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class OrderService {
  private final ReactiveOrderRepository orderRepository;

  public Mono<Void> saveOrder(Order order) {
    return orderRepository.saveOrder(order.getOrderId(), order.getProductId(), order.getAmount());
  }
}
