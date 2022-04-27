package com.stanleypham.mapper;

import java.sql.ResultSet;

public interface ResultSetModelMapper<T> {
    T mappingResultSetToModel(ResultSet resultSet);
}
