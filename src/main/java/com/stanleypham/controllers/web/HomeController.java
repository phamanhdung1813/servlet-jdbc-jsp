package com.stanleypham.controllers.web;

import com.stanleypham.constant.ApplicationConstant;
import com.stanleypham.models.UserModel;
import com.stanleypham.services.ICategoryService;
import com.stanleypham.services.INewsService;
import com.stanleypham.services.IUserService;
import com.stanleypham.utils.FormParseUtils;
import com.stanleypham.utils.SessionUtils;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/web-main", "/login", "/logout"})
public class HomeController extends HttpServlet {
    @Inject
    private ICategoryService iCategoryService;

    @Inject
    private INewsService iNewsService;

    @Inject
    private IUserService iUserService;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("login")) {
            String message = request.getParameter("message");
            String alert = request.getParameter("alert");
            if (message != null && alert != null) {
                request.setAttribute("message", resourceBundle.getString(message));
                request.setAttribute("alert", alert);
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/login.jsp");
            requestDispatcher.forward(request, response);
        } else if (action != null && action.equals("logout")) {
            SessionUtils.getInstance().removeObject(request, ApplicationConstant.USERMODEL);
            response.sendRedirect(request.getContextPath() + "/web-main");
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/web/home.jsp");
            requestDispatcher.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("login")) {
            UserModel userModel = FormParseUtils.toModel(UserModel.class, request);
            userModel = iUserService.findByUsernameAndPasswordAndStatus(
                    userModel.getUsername(),
                    userModel.getPassword(),
                    1
            );
            if (userModel != null) {
                // push userModel to session utils
                SessionUtils.getInstance().putObject(
                        request, ApplicationConstant.USERMODEL, userModel
                );

                if (userModel.getRoleModel().getCode().equals("USER")) {
                    response.sendRedirect(request.getContextPath() + "/web-main");
                } else if (userModel.getRoleModel().getCode().equals("ADMIN")) {
                    response.sendRedirect(request.getContextPath() + "/admin-main");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/login?action=login&alert=danger&message=invalid_info");
            }

        }
    }
}
