package com.webflux.r2dbc.dao;

import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.r2dbc.spi.ConnectionFactoryOptions.DATABASE;
import static io.r2dbc.spi.ConnectionFactoryOptions.DRIVER;
import static io.r2dbc.spi.ConnectionFactoryOptions.HOST;
import static io.r2dbc.spi.ConnectionFactoryOptions.PASSWORD;
import static io.r2dbc.spi.ConnectionFactoryOptions.PORT;
import static io.r2dbc.spi.ConnectionFactoryOptions.PROTOCOL;
import static io.r2dbc.spi.ConnectionFactoryOptions.USER;

@Configuration
public class DatabaseConfiguration {

  @Bean
  public ConnectionFactory connectionFactory() {
    return ConnectionFactories.get(
        ConnectionFactoryOptions.builder()
            .option(DRIVER, "pool")
            .option(PROTOCOL, "mariadb")
            .option(HOST, "localhost")
            .option(PORT, 3306)
            .option(USER, "user")
            .option(PASSWORD, "password")
            .option(DATABASE, "test")
            .build());
  }

  @Bean
  public ConnectionPool connectionPool(ConnectionFactory connectionFactory) {
    ConnectionPoolConfiguration connectionPoolConfiguration =
        ConnectionPoolConfiguration.builder(connectionFactory).maxSize(200)
      .build();
    return new ConnectionPool(connectionPoolConfiguration);
  }
}
