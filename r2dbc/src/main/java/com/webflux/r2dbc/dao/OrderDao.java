package com.webflux.r2dbc.dao;

import com.webflux.r2dbc.models.Order;
import io.r2dbc.pool.ConnectionPool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderDao {

  private final ConnectionPool connectionPool;

  public Mono<Void> saveOrder(Order order) {
    //        Connection connection = connectionPool.create().block();
    //        Statement statement = connection.createStatement("INSERT INTO orders (ORDER_ID,
    // PRODUCT_ID, AMOUNT) VALUES " +
    //                "(?, ?, ?)");
    //        statement.bind(0, order.getOrderId());
    //        statement.bind(1, order.getProductId());
    //        statement.bind(2, order.getAmount());
    //
    //        return Mono.from(statement.execute()).thenReturn(order);
    return Mono.from(
        connectionPool.create()
            .flatMap(
                connection ->
                    Mono.from(connection.beginTransaction())
                                    .then(Mono.from(connection.createStatement(
                                                    "INSERT INTO orders (ORDER_ID, PRODUCT_ID, AMOUNT) "
                                                            + "VALUES (?, ?, ?)")
                                            .bind(0, order.getOrderId())
                                            .bind(1, order.getProductId())
                                            .bind(2, order.getAmount())
                                            .execute()))
                            .doOnNext(result -> log.info("result, {}", result))
                            .then()
                            .delayUntil(r -> connection.commitTransaction())
                        .doFinally(__ -> connection.close())));



  }
}
