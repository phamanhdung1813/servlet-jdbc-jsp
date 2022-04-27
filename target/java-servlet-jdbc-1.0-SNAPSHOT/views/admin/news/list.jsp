<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="ApiUrl" value="/admin-api-news"/>
<c:url var="AllNewsUrl" value="/admin-news"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>View Data Page</title>
</head>

<body>
<form action="<c:url value='/admin-news'/>" id="formSubmit" method="GET">
    <div class="main-content">
        <div class="main-content-inner">
            <div class="page-content">
                <%--#--%>
                <c:if test="${not empty messageResponse}">
                    <div class="alert alert-${alert}">
                            ${messageResponse}
                    </div>
                </c:if>
                <div class="table-btn-controls">
                    <div class="center tableTools-container">
                        <div class="dt-buttons btn-overlap btn-group">
                            <a flag="info"
                               class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                               data-toggle="tooltip"
                               title='Add' href='<c:url value="/admin-news?type=edit"/>'>
															<span>
																<i class="fa fa-plus-circle bigger-110 purple"></i>
															</span>
                            </a>
                            <button id="btnDelete" type="button"
                                    class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
                                    data-toggle="tooltip" title='Delete'>
																<span>
																	<i class="fa fa-trash-o bigger-110 pink"></i>
																</span>
                            </button>
                        </div>
                    </div>
                </div>
                <%--#--%>

                <div class="row">
                    <div class="col-xs-12">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">ID</th>
                                <th scope="col">Title</th>
                                <th scope="col">Content</th>
                                <th scope="col">CategoryID</th>
                                <th scope="col">Created Date</th>
                                <th scope="col">Created By</th>
                                <th scope="col">Modified Date</th>
                                <th scope="col">Modified By</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="i" items="${all_news_model.attributeResultList}">
                                <tr>
                                    <td>
                                        <input type="checkbox" id="checkbox_${i.id}" value="${i.id}">
                                    </td>
                                    <td>
                                        <c:url value="/admin-news" var="updateURL">
                                            <c:param name="type" value="edit"/>
                                            <c:param name="id" value="${i.id}"/>
                                        </c:url>
                                        <a href='${updateURL}'>${i.id}</a>
                                    </td>
                                    <td>${i.title}</td>
                                    <td>${i.content}</td>
                                    <td>${i.categoryId}</td>
                                    <td>${i.createdDate}</td>
                                    <td>${i.createdBy}</td>
                                    <td>${i.modifiedDate}</td>
                                    <td>${i.modifiedBy}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div style="text-align:center">
                            <ul class="pagination" id="pagination"></ul>
                            <input type="hidden" id="currentPage" name="currentPage"/>
                            <input type="hidden" id="visiblePages" name="visiblePages"/>
                            <input type="hidden" id="sortName" name="sortName"/>
                            <input type="hidden" id="sortBy" name="sortBy"/>
                            <input type="hidden" value="" id="type" name="type"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div><!-- /.main-content -->
</form>

<script type='text/javascript'>
    var currentPage = ${all_news_model.currentPage};
    var totalPages = ${all_news_model.totalPages};
    var visiblePages = ${all_news_model.visiblePages};
    var limit = 3
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: visiblePages,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (currentPage != page) {
                    $('#visiblePages').val(limit);
                    $('#currentPage').val(page);
                    $('#sortName').val('id');
                    $('#sortBy').val('desc');
                    $('#type').val('list')
                    $('#formSubmit').submit();
                }
            }
        })
    });

    $('#btnDelete').click(function (e) {
        var data = {};
        var ids = $('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['ids'] = ids;
        deleteNews(data);
    });

    function deleteNews(data) {
        $.ajax({
            url: '${ApiUrl}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                window.location.href = "${AllNewsUrl}?type=list&currentPage=1&visiblePages=3&sortName=id&sortBy=desc&message=delete_success"
            },
            error: function (err) {
                window.location.href = "${AllNewsUrl}?type=list&currentPage=1&visiblePages=3&sortName=id&sortBy=desc&message=error"
            }
        });

    }
</script>
</body>
</html>