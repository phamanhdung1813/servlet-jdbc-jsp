package com.stanleypham.controllers.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stanleypham.constant.ApplicationConstant;
import com.stanleypham.models.NewsModel;
import com.stanleypham.models.UserModel;
import com.stanleypham.services.INewsService;
import com.stanleypham.utils.HttpUtils;
import com.stanleypham.utils.SessionUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin-api-news"})
public class NewsAPI extends HttpServlet {
    @Inject
    private INewsService iNewsService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // mapping json data between client and server
        response.setContentType("application/json");
        HttpUtils httpUtils = HttpUtils.of(request.getReader());

        // mapping String data from httpUtils to model
        NewsModel newsModel = httpUtils.toModel(NewsModel.class);
        UserModel userModel = (UserModel) SessionUtils.getInstance().getObject(request, ApplicationConstant.USERMODEL);
        newsModel.setCreatedBy(userModel.getUsername());
        newsModel = iNewsService.insert(newsModel);

        // Response value to client
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), newsModel);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        HttpUtils httpUtils = HttpUtils.of(request.getReader());
        NewsModel newsModel = httpUtils.toModel(NewsModel.class);
        UserModel userModel = (UserModel) SessionUtils.getInstance().getObject(request, ApplicationConstant.USERMODEL);
        newsModel.setModifiedBy(userModel.getUsername());
        newsModel = iNewsService.update(newsModel);
        ObjectMapper objectMapper = new ObjectMapper();
        // response
        objectMapper.writeValue(response.getOutputStream(), newsModel);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        HttpUtils httpUtils = HttpUtils.of(request.getReader());
        NewsModel newsModel = httpUtils.toModel(NewsModel.class);
        iNewsService.delete(newsModel.getIds());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), "DELETE SUCCESS");
    }
}
