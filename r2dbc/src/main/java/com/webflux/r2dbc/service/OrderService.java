package com.webflux.r2dbc.service;

import com.webflux.r2dbc.models.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class OrderService {

  private final DatabaseClient databaseClient;

  @Transactional
  public Mono<Void> saveOrder(Order order) {

    return databaseClient
        .sql("INSERT INTO orders (order_id, product_id, amount) VALUES (:1,:2,:3)")
        .bind("1", order.getOrderId())
        .bind("2", order.getProductId())
        .bind("3", order.getAmount())
        .then();
  }
}
