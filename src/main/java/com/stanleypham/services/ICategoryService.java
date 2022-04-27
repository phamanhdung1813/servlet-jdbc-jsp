package com.stanleypham.services;

import com.stanleypham.models.CategoryModel;

import java.util.List;

public interface ICategoryService {
    List<CategoryModel> findAll();

    CategoryModel findOneById(Long id);
}

