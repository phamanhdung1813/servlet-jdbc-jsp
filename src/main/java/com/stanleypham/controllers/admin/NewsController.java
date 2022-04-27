package com.stanleypham.controllers.admin;

import com.stanleypham.constant.ApplicationConstant;
import com.stanleypham.models.NewsModel;
import com.stanleypham.pagination.PageRequest;
import com.stanleypham.pagination.Pageble;
import com.stanleypham.pagination.Sorter;
import com.stanleypham.services.ICategoryService;
import com.stanleypham.services.INewsService;
import com.stanleypham.utils.FormParseUtils;
import com.stanleypham.utils.MessageUtils;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin-news"})
public class NewsController extends HttpServlet {
    @Inject
    private INewsService iNewsService;

    @Inject
    private ICategoryService iCategoryService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Mapping Request Parameter from String to Model
        NewsModel newsModel = FormParseUtils.toModel(NewsModel.class, request);
        String url = "";
        if (newsModel.getType().equals(ApplicationConstant.LIST)) {
            Pageble pageble = new PageRequest(newsModel.getCurrentPage(),
                    newsModel.getVisiblePages(),
                    new Sorter(
                            newsModel.getSortName(), newsModel.getSortBy()
                    ));
            newsModel.setAttributeResultList(iNewsService.findAll(pageble));
            int totalPages = (int) Math.ceil((double) iNewsService.getTotalRecords() / newsModel.getVisiblePages());
            newsModel.setTotalPages(totalPages);
            url = "/views/admin/news/list.jsp";
        } else if (newsModel.getType().equals(ApplicationConstant.EDIT)) {
            // update
            if (newsModel.getId() != null) {
                newsModel = iNewsService.findOneById(newsModel.getId());
                // adding
            }

            request.setAttribute("categories", iCategoryService.findAll());
            url = "/views/admin/news/edit.jsp";
        }
        MessageUtils.showMessage(request);
        request.setAttribute(ApplicationConstant.ALL_NEWS_MODEL, newsModel);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
        requestDispatcher.forward(request, response);
    }
}
