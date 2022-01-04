package com.webflux.jdbc.service;

import com.webflux.jdbc.dao.OrdersDao;
import com.webflux.jdbc.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class OrderService {
  private final OrdersDao ordersDao;

  @Transactional
  public void saveOrder(Order order) {
    ordersDao.save(order);
  }
}
