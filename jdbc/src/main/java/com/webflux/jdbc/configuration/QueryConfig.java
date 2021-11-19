package com.webflux.jdbc.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class QueryConfig {

    @Value("${db.query.fetch-size:1000}")
    protected int fetchSize;

    @Value("${db.query.timeout:600}")
    protected int queryTimeout;
}
