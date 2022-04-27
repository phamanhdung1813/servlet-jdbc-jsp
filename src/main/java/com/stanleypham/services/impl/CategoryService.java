package com.stanleypham.services.impl;

import com.stanleypham.dao.ICategoryDao;
import com.stanleypham.models.CategoryModel;
import com.stanleypham.services.ICategoryService;

import javax.inject.Inject;
import java.util.List;

public class CategoryService implements ICategoryService {
    @Inject
    private ICategoryDao IcategoryDao;

    @Override
    public List<CategoryModel> findAll() {
        return IcategoryDao.findAll();
    }

    @Override
    public CategoryModel findOneById(Long id) {
        return IcategoryDao.findOneById(id);
    }
}
