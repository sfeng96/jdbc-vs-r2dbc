package com.webflux.jdbc.dao;

import com.webflux.jdbc.configuration.QueryConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public abstract class BaseDao {

  protected final DataSource dataSource;
  protected final NamedParameterJdbcTemplate regularTemplate;

  protected BaseDao(DataSource dataSource, QueryConfig queryConfig) {
    this.dataSource = dataSource;
    JdbcTemplate delegate = new JdbcTemplate(dataSource);
    delegate.setFetchSize(queryConfig.getFetchSize());
    delegate.setQueryTimeout(queryConfig.getQueryTimeout());
    this.regularTemplate = new NamedParameterJdbcTemplate(delegate);
  }

  protected static String columns(Set<String> cols) {
    return cols.stream().reduce((p, c) -> p + ", " + c).orElseThrow();
  }

  protected static String args(Set<String> cols) {
    return cols.stream().map(p -> ":" + p).collect(Collectors.joining(","));
  }
}
