package com.stanleypham.services;

import com.stanleypham.models.NewsModel;
import com.stanleypham.pagination.Pageble;

import java.util.List;

public interface INewsService {
    List<NewsModel> findAllByCategoryId(Long categoryId);

    List<NewsModel> findAll(Pageble pageble);

    NewsModel insert(NewsModel newsModel);

    NewsModel update(NewsModel newsModel);

    int getTotalRecords();

    void delete(long[] ids);

    NewsModel findOneById(Long id);
}
