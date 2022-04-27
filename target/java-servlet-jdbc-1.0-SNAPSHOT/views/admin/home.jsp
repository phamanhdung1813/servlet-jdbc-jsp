<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin Main Page</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href='<c:url value ="/web-main"/>'>HOME</a>
                </li>
                <li>
                    <i class="glyphicon glyphicon-search"></i>
                    <a href='<c:url value ="/admin-news?type=list&currentPage=1&visiblePages=3&sortName=id&sortBy=desc"/>'>VIEW DATA</a>
                </li>
            </ul>
        </div>
        <div class="page-content">
            <h3>Data Interaction with JSP: </h3>
            <ul>
                <li>Java JDBC, Servlet, JSP template, JSTL, RestAPI.</li>
                <li>Data interaction with JQuery, AJAX.</li>
                <li>CRUD, Pagination, Sorting, Join Table.</li>
            </ul>

            <h3>API URLs</h3>
            <ul>
                <li style="color: red">/web-main:</li>
                👉 All of users are allowed to access
                <li style="color: red">/admin-* (/admin-main, /admin-news, /admin-api-news):</li>
                👉 Only admin is allowed to access
                <li style="color: red">/admin-news?type=list&currentPage=1&visiblePages=3&sortName=id&sortBy=desc</li>
                👉 View data URL with Pagination and Sorting
                <li style="color: red">/admin-news?type=edit</li>
                👉 Adding data
                <li style="color: red">/admin-news?type=edit&id=123</li>
                👉 Updating Data, Deleting Data
            </ul>
        </div>
    </div>
</div>
</body>
</html>