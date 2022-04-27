package com.stanleypham.dao;

import com.stanleypham.models.NewsModel;
import com.stanleypham.pagination.Pageble;

import java.util.List;

public interface INewsDao extends GenericDao<NewsModel> {
    List<NewsModel> findAllByCategoryId(Long categoryId);

    NewsModel findOneById(Long id);

    Long insert(NewsModel newsModel);

    void update(NewsModel newsModel);

    int getTotalRecords();

    List<NewsModel> findAll(Pageble pageble);

    void delete(long id);

}
