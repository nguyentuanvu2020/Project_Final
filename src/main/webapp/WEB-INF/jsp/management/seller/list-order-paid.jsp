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
                <%@include file="../../include-management/menumanagement-back.jsp" %>
                <div class="col-md-10">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="content-box-large">
                                <div class="panel-heading">
                                    <div class="panel-title">List Shipping Order</div>
                                </div>
                                <div class="panel-body">
                                    <div style="margin-bottom: 5px;">
                                        <input id="from-date" type="date"><span>&nbsp;to&nbsp;</span><input id="to-date" class="control-label" type="date">&nbsp;&#9679;&nbsp;<input class="btn btn-xs btn-primary" type="button" onclick="searchOrderByDate()" value="Search">
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
                                            <c:forEach var="order" items="${listPaidOrders}">
                                                <tr class="odd gradeX">
                                                    <td class="mouser-hover" onclick="location.href = '<c:url value="order-detail/${order.id}"/>'">${i}</td>
                                                    <td class="mouser-hover" onclick="location.href = '<c:url value="order-detail/${order.id}"/>'">${order.id}</td>
                                                    <td class="mouser-hover" onclick="location.href = '<c:url value="order-detail/${order.id}"/>'">${order.orderDate}</td>
                                                    <td class="mouser-hover"onclick="location.href = '<c:url value="order-detail/${order.id}"/>'">${order.note}</td>
                                                    <td onclick="location.href = '<c:url value="order-detail/${order.id}"/>'"><fmt:formatNumber minFractionDigits="0" type="number" value="${order.totalPrice}"/></td>
                                                    <td>
                                                        <p class="input-sm">${order.orderStatus}</p>
                                                    </td>
                                                    <td>
                                                        <button class="btn btn-xs btn-warning" onclick="location.href = '<c:url value="update-status-new/${order.id}"/>'">RETURN</button>
                                                        <button class="btn btn-xs btn-danger" onclick="location.href = '<c:url value="cancel-order/${order.id}"/>'">CANCEL</button>
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
