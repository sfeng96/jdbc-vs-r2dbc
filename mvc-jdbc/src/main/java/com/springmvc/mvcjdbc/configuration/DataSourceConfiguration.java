package com.springmvc.mvcjdbc.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public abstract class DataSourceConfiguration {

  @Value("${db.username:user}")
  protected String username;

  @Value("${db.password:password}")
  protected String password;

  @Value("${db.url}")
  protected String url;

  @Value("${db.driver}")
  protected String driver;

  @Value("${db.pool.min.idle:10}")
  protected int minIdle;

  @Value("${db.pool.max.size:200}")
  protected int maxSize;

  @Value("${db.pool.idle.timeout:300000}")
  protected int idleTimeoutMs;

  @Value("${db.pool.connection.timeout:600000}")
  protected int connectionTimeoutMs;

  @Value("${db.pool.connection.maxlife:3600000}")
  protected int connectionMaxLifeMs;

  @Value("${db.pool.validation.timeout:5000}")
  protected int validationTimeoutMs;

  @Value("${db.pool.validation.query: SELECT version()}")
  protected String validationQuery;

  protected HikariDataSource dataSource;

  public abstract DataSource dataSource() throws SQLException;

  protected HikariConfig getHikariConfig() {
    HikariConfig conf = new HikariConfig();
    conf.setMinimumIdle(minIdle);
    conf.setMaximumPoolSize(maxSize);
    conf.setConnectionTimeout(connectionTimeoutMs);
    conf.setIdleTimeout(idleTimeoutMs);
    conf.setMaxLifetime(connectionMaxLifeMs);
    conf.setValidationTimeout(validationTimeoutMs);
    conf.setLeakDetectionThreshold(0);
    conf.setConnectionInitSql(validationQuery);
    conf.setConnectionTestQuery(validationQuery);
    conf.setAutoCommit(false);
    return conf;
  }
}
