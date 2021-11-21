package com.springmvc.mvcjdbc.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class BasicDataSourceConfig extends DataSourceConfiguration {

  @Override
  @Bean
  public DataSource dataSource() {
    HikariConfig config = getHikariConfig();
    config.setJdbcUrl(this.url);
    config.setUsername(this.username);
    config.setPassword(this.password);
    config.setDriverClassName(this.driver);
    dataSource = new HikariDataSource(config);
    return dataSource;
  }

  @Bean
    public PlatformTransactionManager getTransactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }
}
