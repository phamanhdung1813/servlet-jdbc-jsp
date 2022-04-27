<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="ApiUrl" value="/admin-api-news"/>
<c:url var="AllNewsUrl" value="/admin-news"/>
<html>
<head>
    <title>Edit News</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Main Page</a>
                </li>
                <li class="active">Edit News</li>
            </ul>
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-${alert}">
                                ${messageResponse}
                        </div>
                    </c:if>
                    <form id="formNewsSubmit">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Category</label>
                            <div class="col-sm-9">
                                <select class="form-control" id="categoryId" name="categoryId">
                                    <c:if test="${empty all_news_model.categoryId}">
                                        <option value="">---Choose News Category---</option>
                                        <c:forEach var="i" items="${categories}">
                                            <option value="${i.id}">${i.name}</option>
                                        </c:forEach>
                                    </c:if>

                                    <c:if test="${not empty all_news_model.categoryId}">
                                        <option value="">---Choose News Category---</option>
                                        <c:forEach var="i" items="${categories}">
                                            <option
                                                    value="${i.id}"
                                                    <c:if test="${i.id == all_news_model.categoryId}">selected="selected"</c:if>>
                                                    ${i.name}
                                            </option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Title</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="title" name="title"
                                       value="${all_news_model.title}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Content</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="content" name="content"
                                       value="${all_news_model.content}"/>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <c:if test="${not empty all_news_model.id}">
                                    <input type="button" id="btnAddUpdate" class="btn btn-white btn-warning btn-bold"
                                           value="Update News"/>
                                </c:if>
                                <c:if test="${empty all_news_model.id}">
                                    <input type="button" id="btnAddUpdate" class="btn btn-white btn-warning btn-bold"
                                           value="Adding News"/>
                                </c:if>
                            </div>
                        </div>
                        <input type="hidden" value="${all_news_model.id}" id="id" name="id"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $('#btnAddUpdate').click(function (e) {
        e.preventDefault();
        var data = {};
        var formNewsSubmit = $('#formNewsSubmit').serializeArray();
        $.each(formNewsSubmit, function (i, j) {
            data["" + j.name + ""] = j.value;
        });
        var id = $('#id').val();
        if (id == "") {
            addNews(data);
        } else {
            updateNews(data);
        }
    });

    function addNews(data) {
        $.ajax({
            url: '${ApiUrl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (res) {
                <%--window.location.href = "${AllNewsUrl}?type=edit&id=" + res.id + "&message=add_success"--%>
                window.location.href = "${AllNewsUrl}?type=list&currentPage=1&visiblePages=3&sortName=id&sortBy=desc&message=add_success"
            },
            error: function (err) {
                window.location.href = "${AllNewsUrl}?type=list&currentPage=1&visiblePages=3&sortName=id&sortBy=desc&message=error"
            }
        });

    }

    function updateNews(data) {
        $.ajax({
            url: '${ApiUrl}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (res) {
                <%--window.location.href = "${AllNewsUrl}?type=edit&id=" + res.id + "&message=update_success"--%>
                window.location.href = "${AllNewsUrl}?type=list&currentPage=1&visiblePages=3&sortName=id&sortBy=desc&message=update_success"
            },
            error: function () {
                window.location.href = "${AllNewsUrl}?type=list&currentPage=1&visiblePages=3&sortName=id&sortBy=desc&message=error"
            }
        });
    }
</script>
</body>
</html>