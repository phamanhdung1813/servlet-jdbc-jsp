package com.stanleypham.mapper;

import com.stanleypham.models.CategoryModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements ResultSetModelMapper<CategoryModel> {
    @Override
    public CategoryModel mappingResultSetToModel(ResultSet resultSet) {
        try {
            CategoryModel categoryModel = new CategoryModel();
            categoryModel.setId(resultSet.getLong("id"));
            categoryModel.setName(resultSet.getString("name"));
            categoryModel.setCode(resultSet.getString("code"));
            return categoryModel;
        } catch (SQLException e) {
            return null;
        }
    }
}
