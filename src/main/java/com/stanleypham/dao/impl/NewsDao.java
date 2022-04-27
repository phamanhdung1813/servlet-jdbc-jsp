package com.stanleypham.dao.impl;

import com.stanleypham.dao.INewsDao;
import com.stanleypham.mapper.NewsMapper;
import com.stanleypham.models.NewsModel;
import com.stanleypham.pagination.Pageble;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class NewsDao extends AbstractDao<NewsModel> implements INewsDao {
    @Override
    public List<NewsModel> findAllByCategoryId(Long categoryId) {
        String sql = "SELECT * FROM news WHERE categoryid = ?";
        return sqlQuery(sql, new NewsMapper(), categoryId);
    }

    @Override
    public NewsModel findOneById(Long id) {
        String sql = "SELECT * FROM news WHERE id = ?";
        List<NewsModel> newsModelList = sqlQuery(sql, new NewsMapper(), id);
        return newsModelList.isEmpty() ? null : newsModelList.get(0);
    }

    @Override
    public Long insert(NewsModel newsModel) {
        String sql = "INSERT INTO news (title, content, categoryid, createdby, createddate) VALUES (?, ?, ?, ?, ?)";
        return insert(sql, newsModel.getTitle(), newsModel.getContent(), newsModel.getCategoryId(), newsModel.getCreatedBy(), newsModel.getCreatedDate());
    }

    @Override
    public void update(NewsModel newsModel) {
        String sql = "UPDATE news SET title = ?, content = ?, categoryid = ?, createddate = ?, createdby = ?, modifieddate = ?, modifiedby = ? WHERE id = ?";
        updateDelete(sql, newsModel.getTitle(), newsModel.getContent(), newsModel.getCategoryId(),
                newsModel.getCreatedDate(),
                newsModel.getCreatedBy(), newsModel.getModifiedDate(),
                newsModel.getModifiedBy(),
                newsModel.getId());
    }

    @Override
    public List<NewsModel> findAll(Pageble pageble) {
        StringBuilder sql = new StringBuilder("SELECT * FROM news");
        if (pageble.getSorter() != null
                && StringUtils.isNotBlank(pageble.getSorter().getSortName())
                && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
            sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + " ");
        }
        if (pageble.getOffset() != null && pageble.getLimit() != null) {
            sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit());
        }
        return sqlQuery(sql.toString(), new NewsMapper());
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM news WHERE id = ?";
        updateDelete(sql, id);
    }

    @Override
    public int getTotalRecords() {
        String sql = "SELECT count(*) FROM news";
        return count(sql);
    }
}
