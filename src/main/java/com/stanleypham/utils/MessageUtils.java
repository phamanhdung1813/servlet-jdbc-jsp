package com.stanleypham.utils;

import javax.servlet.http.HttpServletRequest;

public class MessageUtils {
    public static void showMessage(HttpServletRequest request) {
        if (request.getParameter("message") != null) {
            String content = "";
            String alert = "";
            String message = request.getParameter("message");
            if (message.equals("add_success")) {
                content = "Adding success !!!";
                alert = "success";
            } else if (message.equals("update_success")) {
                content = "Update success !!!";
                alert = "success";
            } else if (message.equals("delete_success")) {
                content = "Delete success !!!";
                alert = "success";
            } else if (message.equals("error")) {
                content = "ERROR !!!";
                alert = "danger";
            }
            request.setAttribute("messageResponse", content);
            request.setAttribute("alert", alert);
        }
    }
}
