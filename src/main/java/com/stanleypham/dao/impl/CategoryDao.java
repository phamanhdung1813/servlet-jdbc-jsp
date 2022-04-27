package com.stanleypham.dao.impl;

import com.stanleypham.dao.ICategoryDao;
import com.stanleypham.mapper.CategoryMapper;
import com.stanleypham.models.CategoryModel;

import java.util.List;

public class CategoryDao extends AbstractDao<CategoryModel> implements ICategoryDao {

    @Override
    public List<CategoryModel> findAll() {
        String sql = "SELECT * FROM category";
        return sqlQuery(sql, new CategoryMapper());
    }

    @Override
    public CategoryModel findOneById(Long id) {
        String sql = "SELECT * FROM category WHERE id = ?";
        List<CategoryModel> categoryModelList = sqlQuery(sql, new CategoryMapper(), id);
        return categoryModelList.isEmpty() ? null : categoryModelList.get(0);
    }

    @Override
    public CategoryModel findOneByCategoryCode(String code) {
        String sql = "SELECT * FROM category WHERE code = ?";
        List<CategoryModel> categoryModelList = sqlQuery(sql, new CategoryMapper(), code);
        return categoryModelList.isEmpty() ? null : categoryModelList.get(0);
    }

}