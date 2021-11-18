package com.webflux.r2dbc.repository;

import com.webflux.r2dbc.model.Order;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Repository
public interface ReactiveOrderRepository extends ReactiveCrudRepository<Order, String> {

    @Query("INSERT INTO orders (order_id, product_id, amount) VALUES (:1,:2,:3)")
    Mono<Void> saveOrder(String orderId, String productId, BigDecimal amount);

    Flux<Order> findOrdersByOrderIdAndProductId(String orderId, String productId);
}
