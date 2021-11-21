package com.springmvc.mvcjdbc.dao;

import com.springmvc.mvcjdbc.configuration.QueryConfig;
import com.springmvc.mvcjdbc.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Slf4j
public class OrdersDao extends BaseDao {

    private static final Map<String, Integer> map = new LinkedHashMap<>();

    static {
        map.put("ORDER_ID", Types.VARCHAR);
        map.put("PRODUCT_ID", Types.VARCHAR);
        map.put("AMOUNT", Types.DECIMAL);
    }

    public static final String ORDERS_INSERT =
            "insert into orders (" + columns(map.keySet()) + ") values (" + args(map.keySet()) + ")";

    protected OrdersDao(DataSource dataSource, QueryConfig queryConfig) {
        super(dataSource, queryConfig);
    }

    @Transactional
    public void save(Order order) {
        if (order != null) {
            SqlParameterSource namedParameter = getMapSqlParameterSource(order);
            try{
                this.regularTemplate.update(ORDERS_INSERT, namedParameter);
            } catch (DuplicateKeyException duplicateKeyException){
                log.warn("Duplicate data in the message");
            }
        } else {
            log.warn("Insert failed, null object");
        }
    }

    private SqlParameterSource getMapSqlParameterSource(Order order) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        prepareEntryMap(order).forEach((k, v) -> namedParameters.addValue(k, v, map.get(k)));
        return namedParameters;
    }

    private Map<String, Object> prepareEntryMap(Order order) {
        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("ORDER_ID", order.getOrderId());
        valueMap.put("PRODUCT_ID", order.getProductId());
        valueMap.put("AMOUNT", order.getAmount());
        return valueMap;
    }
}
