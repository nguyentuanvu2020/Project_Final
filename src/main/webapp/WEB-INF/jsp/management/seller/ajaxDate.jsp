<%-- 
    Document   : ajaxDate
    Created on : Nov 1, 2019, 7:59:41 PM
    Author     : phand
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="i" value="1"/>
<c:forEach var="order" items="${processingOders}">
    <tr class="odd gradeX mouser-hover" onclick="location.href = '<c:url value="order-detail/${order.id}"/>'">
        <td>${i}</td>
        <td>${order.id}</td>
        <td>${order.orderDate}</td>
        <td>${order.note}</td>
        <td><fmt:formatNumber minFractionDigits="0" type="number" value="${order.totalPrice}"/></td>
</tr>
<c:set var="i" value="${i+1}"/>
</c:forEach>
