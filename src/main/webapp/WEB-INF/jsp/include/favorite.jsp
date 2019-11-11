

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--danh mục yêu thích-->
<div class="container mb15 group-index">
    <div class="row">
        <div class="col-xs-12">
            <div class="title-block">
                <div class="wrap-content">
                    <h3 class="title-group">Danh mục yêu thích</h3>
                    <div class="title-group-note">Danh mục được nhiều khách hàng yêu thích</div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <c:forEach var="product" items="${allProduct}">
            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-6 mb30 box-banner-index">
                <img src="${pageContext.request.contextPath}/resources/image/${product.listImageProductDetail[0].name}" class="img-rounded"/>
            </div>
        </c:forEach>
    </div>
</div>
<!--end danh mục yêu thích-->
