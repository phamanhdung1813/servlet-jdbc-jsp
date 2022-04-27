package com.stanleypham.dao;

import com.stanleypham.mapper.ResultSetModelMapper;

import java.util.List;

public interface GenericDao<T> {
    <T> List<T> sqlQuery(String sqlQuery, ResultSetModelMapper<T> resultSetModelMapper, Object... parameters);

    Long insert(String sqlQuery, Object... parameters);

    void updateDelete(String sqlQuery, Object... parameters);

    int count(String sqlQuery, Object... parameters);

}
