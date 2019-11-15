<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <!--include headercss-->
    <%@include file="../../include-management/headcss.jsp" %>
    <body>
        <!--include header--> 
        <%@include file="../../include-management/header.jsp" %>
        <div class="page-content">
            <div class="row">
                <!--include menu-->
                <%@include file="../../include-management/menumanagement.jsp" %>
                <div class="col-md-10">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="content-box-large">
                                <div class="panel-heading">
                                    <div class="panel-title">List product</div>
                                </div>
                                <div class="panel-body">
                                    <p id="returntext">${info}</p>
                                    <p id="returntext">${thongbao}</p>

                                    <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>ID</th>
                                                <th>Name</th>
                                                <th>Price</th>
                                                <th>Quantity</th>
                                                <th>Status</th>
                                                <th>Category</th>
                                                <th>Options</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:set var="i" value="1"/>
                                            <c:forEach var="product" items="${products}">
                                                <tr class="odd gradeX">
                                                    <td>${i}</td>
                                                    <td>${product.id}</td>
                                                    <td>${product.name}</td>
                                                    <td class="center"><fmt:formatNumber minFractionDigits="0" type="number" value="${product.price}"/></td>
                                                    <td class="center">
                                                        <c:forEach var="detail" items="${product.listProductDetail}">
                                                            <p>
                                                                ${detail.color.productColor} - ${detail.productSize.productSize} - ${detail.productQuantity}
                                                            </p>
                                                        </c:forEach>
                                                    </td>
                                                    <td class="center">${product.status}</td>
                                                    <td class="center">${product.category.name}</td>
                                                    <td class="center">
                                                        <button class="btn btn-primary btn-xs" onclick="location.href = '<c:url value="${request.contextPath}/management/manager/update-product/${product.id}"/>'">
                                                            <i class="glyphicon glyphicon-refresh"></i> 
                                                            Update
                                                        </button>
                                                        <c:choose>
                                                            <c:when test="${product.status=='NO'}">
                                                                <button class="btn btn-success btn-xs" onclick="location.href = '<c:url value="${request.contextPath}/management/manager/update-status/${product.id}'"/>">
                                                                    <i class="glyphicon glyphicon-ok"></i>
                                                                    Enable
                                                                </button>
                                                            </c:when>
                                                            <c:when test="${product.status=='YES'}">
                                                                <button class="btn btn-danger  btn-xs" onclick="location.href = '<c:url value="${request.contextPath}/management/manager/update-status/${product.id}'"/>">
                                                                    <i class="glyphicon glyphicon-remove"></i>
                                                                    Disable
                                                                </button>
                                                            </c:when>
                                                        </c:choose>

                                                    </td>
                                                </tr>
                                                <c:set var="i" value="${i+1}"/>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12 panel-danger" style="margin: auto;">


                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--include footer-->
        <%@include file="../../include-management/footer.jsp" %>
    </body>
</html>
