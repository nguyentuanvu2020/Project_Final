<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                                    <div class="panel-title">List Processing Order</div>
                                </div>
                                <div class="panel-body">
                                    <div style="margin-bottom: 5px;">
                                        <f:form action="search" method="get">
                                            <select name="ods" class="btn btn-default btn-xs">
                                                <c:forEach var="s" items="${oderStatus}">
                                                    <option value="${s}">${s}</option> 
                                                </c:forEach>
                                            </select>
                                            <input class="btn btn-default btn-xs" name="s-date" type="date"><span>&nbsp;to&nbsp;</span>
                                            <input class="btn btn-default btn-xs" name="e-date" class="control-label" type="date"><span>&nbsp;&#9679;&nbsp;</span>
                                            <input class="btn btn-xs btn-primary" type="submit" value="Search">
                                        </f:form>
                                    </div>
                                    <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Order number</th>
                                                <th>Order date</th>
                                                <th>Note</th>
                                                <th>Total price</th>
                                                <th>Status</th>
                                                <th>Options</th>
                                            </tr>
                                        </thead>
                                        <tbody id="new-data">
                                            <c:set var="i" value="1"/>
                                            <c:forEach var="order" items="${processingOders}">
                                                <tr class="odd gradeX">
                                                    <td class="mouser-hover" onclick="location.href = '<c:url value="${request.contextPath}/management/seller/order-detail/${order.id}"/>'">${i}</td>
                                                    <td class="mouser-hover" onclick="location.href = '<c:url value="${request.contextPath}/management/seller/order-detail/${order.id}"/>'">${order.id}</td>
                                                    <td class="mouser-hover" onclick="location.href = '<c:url value="${request.contextPath}/management/seller/order-detail/${order.id}"/>'">${order.orderDate}</td>
                                                    <td class="mouser-hover"onclick="location.href = '<c:url value="${request.contextPath}/management/seller/order-detail/${order.id}"/>'">${order.note}</td>
                                                    <td onclick="location.href = '<c:url value="${request.contextPath}/management/seller/order-detail/${order.id}"/>'"><fmt:formatNumber minFractionDigits="0" type="number" value="${order.totalPrice}"/></td>
                                                    <td>
                                                        <p class="input-sm">${order.orderStatus}</p>
                                                    </td>
                                                    <td>
                                                        <button class="btn btn-xs btn-warning" style="margin: 1px;" onclick="location.href = '<c:url value="${request.contextPath}/management/seller/update-status-new/${order.id}"/>'">SHIP</button>
                                                        <button class="btn btn-xs btn-danger" style="margin: 1px;" onclick="location.href = '<c:url value="${request.contextPath}/management/seller/cancel-order/${order.id}"/>'">CANCEL</button>
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
