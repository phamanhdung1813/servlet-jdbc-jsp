package com.stanleypham.services.impl;

import com.stanleypham.dao.ICategoryDao;
import com.stanleypham.dao.INewsDao;
import com.stanleypham.models.CategoryModel;
import com.stanleypham.models.NewsModel;
import com.stanleypham.pagination.Pageble;
import com.stanleypham.services.INewsService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class NewsService implements INewsService {
    @Inject
    private INewsDao iNewsDao;

    @Inject
    private ICategoryDao iCategoryDao;

    @Override
    public List<NewsModel> findAllByCategoryId(Long categoryId) {
        return iNewsDao.findAllByCategoryId(categoryId);
    }

    @Override
    public List<NewsModel> findAll(Pageble pageble) {
        return iNewsDao.findAll(pageble);
    }

    @Override
    public NewsModel insert(NewsModel newsModel) {
        newsModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        Long id = iNewsDao.insert(newsModel);
        return iNewsDao.findOneById(id);
    }

    @Override
    public NewsModel update(NewsModel newsModel) {
        NewsModel oldNews = iNewsDao.findOneById(newsModel.getId());
        newsModel.setCreatedBy(oldNews.getCreatedBy());
        newsModel.setCreatedDate(oldNews.getCreatedDate());
        newsModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        CategoryModel categoryModel = iCategoryDao.findOneById(
                newsModel.getCategoryId()
        );
        newsModel.setCategoryId(categoryModel.getId());
        iNewsDao.update(newsModel);
        return iNewsDao.findOneById(newsModel.getId());
    }

    @Override
    public int getTotalRecords() {
        return iNewsDao.getTotalRecords();
    }

    @Override
    public void delete(long[] ids) {
        for (long id : ids) {
            iNewsDao.delete(id);
        }
    }

    @Override
    public NewsModel findOneById(Long id) {
        NewsModel newsModel = iNewsDao.findOneById(id);
        CategoryModel categoryModel = iCategoryDao.findOneById(newsModel.getCategoryId());
        newsModel.setCategoryId(categoryModel.getId());
        return iNewsDao.findOneById(id);
    }
}
