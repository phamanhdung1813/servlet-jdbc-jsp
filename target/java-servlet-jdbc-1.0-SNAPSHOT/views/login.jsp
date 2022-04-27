<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
</head>
<body>
<div class="container">
    <div class="login-form">
        <div class="main-div">
            <c:if test="${not empty message}">
                <div class="alert alert-${alert}">
                    <strong>${message}</strong>
                </div>
            </c:if>

            <form action="<c:url value="/login"/>" id="formLogin" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" id="username" name="username"
                           placeholder="Username...">
                </div>

                <div class="form-group">
                    <input type="password" class="form-control" id="password" name="password"
                           placeholder="Password...">
                </div>
                <input type="hidden" value="login" name="action"/>
                <button type="submit" class="btn btn-primary">Login</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>