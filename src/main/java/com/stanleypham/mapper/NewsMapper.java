package com.stanleypham.mapper;

import com.stanleypham.models.NewsModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsMapper implements ResultSetModelMapper<NewsModel> {
    @Override
    public NewsModel mappingResultSetToModel(ResultSet resultSet) {
        try {
            NewsModel newsModel = new NewsModel();
            newsModel.setId(resultSet.getLong("id"));
            newsModel.setContent(resultSet.getString("content"));
            newsModel.setTitle(resultSet.getString("title"));
            newsModel.setCategoryId(resultSet.getLong("categoryid"));
            newsModel.setCreatedBy(resultSet.getString("createdby"));
            newsModel.setCreatedDate(resultSet.getTimestamp("createddate"));
            if (resultSet.getString("modifiedby") != null) {
                newsModel.setModifiedBy(resultSet.getString("modifiedby"));
            }
            if (resultSet.getTimestamp("modifieddate") != null) {
                newsModel.setModifiedDate(resultSet.getTimestamp("modifieddate"));
            }
            return newsModel;
        } catch (SQLException e) {
            return null;
        }
    }
}
