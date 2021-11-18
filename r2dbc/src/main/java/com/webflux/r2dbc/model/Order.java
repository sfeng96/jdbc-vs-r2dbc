package com.webflux.r2dbc.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Data
@Table("orders")
public class Order {

    @Id
    @Column("ORDER_ID")
    private String orderId;

    @Column("PRODUCT_ID")
    private String productId;

    @Column("AMOUNT")
    private BigDecimal amount;
}
