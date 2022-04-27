<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Main Page</title>
</head>
<body>
<div>
    <br/>
    <br/>
    <h3>This project was built on Java core language:</h3>
    <ul>
        <li>Java JDBC, Servlet, JSP template, JSTL, MVC structure.</li>
        <li>Simple design pattern, OOP with generic classes.</li>
        <li>Authentication, Authorization process with Servlet Filtering.</li>
        <li>Data interaction with JQuery, AJAX.</li>
    </ul>

    <h3>API URLs</h3>
    <ul>
        <li style="color: red">/web-main:</li>
        👉 All of users are allowed to access
        <li style="color: red">/admin-* (/admin-main, /admin-news, /admin-api-news):</li>
        👉 Only admin is allowed to access
    </ul>

    <h3> Simple data design: </h3>
    <br/>
    <img src="<c:url value="/template/images/news_category.PNG"/>" alt="news_category" style="width:70%"/>

    <h3> Authentication, Authorization database design: </h3>
    <br/>
    <img src="<c:url value="/template/images/users_role.PNG"/>" alt="users_role" style="width:70%"/>

</div>
</body>
</html>