<%-- 
    Document   : ajaxDate
    Created on : Nov 1, 2019, 7:59:41 PM
    Author     : phand
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="i" value="1"/>
<c:forEach var="order" items="${processingOders}">
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
            <button class="btn btn-sm btn-warning" onclick="location.href = '<c:url value="update-status-new/${order.id}"/>'">Update status</button>
            <button class="btn btn-sm btn-danger">Cancel</button>
        </td>
    </tr>
    <c:set var="i" value="${i+1}"/>
</c:forEach>
