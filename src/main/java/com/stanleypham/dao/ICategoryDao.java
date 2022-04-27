package com.stanleypham.dao;

import com.stanleypham.models.CategoryModel;

import java.util.List;

public interface ICategoryDao extends GenericDao<CategoryModel> {
    List<CategoryModel> findAll();

    CategoryModel findOneById(Long id);

    CategoryModel findOneByCategoryCode(String code);
}
