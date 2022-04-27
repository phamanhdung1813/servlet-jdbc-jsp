package com.stanleypham.filters;

import com.stanleypham.constant.ApplicationConstant;
import com.stanleypham.models.UserModel;
import com.stanleypham.utils.SessionUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationServletFilter implements Filter {
    private ServletContext servletContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.servletContext = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        if (url.startsWith(request.getContextPath() + "/admin")) {
            UserModel userModel = (UserModel) SessionUtils.getInstance().getObject(request, ApplicationConstant.USERMODEL);
            if (userModel != null) {
                if (userModel.getRoleModel().getRole().equals("ADMIN")) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else if (userModel.getRoleModel().getRole().equals("USER")) {
                    response.sendRedirect(request.getContextPath() + "/login?action=login&message=invalid_permission&alert=danger");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/login?action=login&message=not_login&alert=danger");
            }
        } else {
            // dont need filter
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
