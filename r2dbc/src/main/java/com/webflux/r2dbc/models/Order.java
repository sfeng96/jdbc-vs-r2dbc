package com.webflux.r2dbc.models;

import lombok.Data;;

import java.math.BigDecimal;

@Data
public class Order {

  private String orderId;

  private String productId;

  private BigDecimal amount;
}
