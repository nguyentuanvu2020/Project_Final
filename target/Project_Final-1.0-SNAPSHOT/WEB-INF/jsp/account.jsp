<%-- 
    Document   : account
    Created on : Nov 1, 2019, 3:52:55 PM
    Author     : motvo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>account page</h1>
        ${username.name}
        <a href="logout">log out</a>
        <p>Lịch sử Order</p>
        <c:forEach var="order" items="${listOrder}">
            ${order.orderDate} <br>
        </c:forEach>
    </body>
</html>
