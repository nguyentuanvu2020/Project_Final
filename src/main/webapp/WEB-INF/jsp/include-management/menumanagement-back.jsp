<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  uri="http://www.springframework.org/security/tags" prefix="sec" %>
<div class="col-md-3">
    <div class="sidebar content-box" style="display: block;">
        <ul class="nav">
            <!-- Main menu -->
            <li class="current"><a href="<c:url value="/management/"/>"><i class="glyphicon glyphicon-home"></i> Dashboard</a></li>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li class="submenu">
                    <a href="#">
                        <i class="glyphicon glyphicon-list"></i> Manage Account
                        <span class="caret pull-right"></span>
                    </a>
                    <!-- Sub menu -->
                    <ul>
                        <li><a href="<c:url value="../admin/add-account"/>">Add account</a></li>
                        <li><a href="<c:url value="../admin/update-role"/>">Update role</a></li>
                        <li><a href="<c:url value="../admin/active-account"/>">Active account</a></li>
                        <li><a href="<c:url value="../admin/block-account"/>">Block account</a></li>
                    </ul>
                </li>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_MANAGER')">
                <li class="submenu">
                    <a href="#">
                        <i class="glyphicon glyphicon-pencil"></i> Manage Product
                        <span class="caret pull-right"></span>
                    </a>
                    <!-- Sub menu -->
                    <ul>
                        <li><a href="<c:url value="../manager/add-new-product"/>"><i class="glyphicon glyphicon-plus"></i> Add product</a></li>
                        <li><a href="<c:url value="../manager/list-product"/>"><i class="glyphicon glyphicon-list"></i> List product</a></li>
                        <li><a href="<c:url value="../manager/list-product"/>"><i class="glyphicon glyphicon-list"></i> List best selling product</a></li>
                    </ul>
                </li>
                <li class="submenu">
                    <a href="#">
                        <i class="glyphicon glyphicon-pencil"></i> Manage Promotion
                        <span class="caret pull-right"></span>
                    </a>
                    <!-- Sub menu -->
                    <ul>
                        <li><a href="<c:url value="../manager/add-promotion"/>"><i class="glyphicon glyphicon-plus"></i> Add promotion</a></li>
                        <li><a href="<c:url value="../manager/list-promotion"/>"><i class="glyphicon glyphicon-repeat"></i> List promotion</a></li>
                    </ul>
                </li>
                <li class="submenu">
                    <a href="#">
                        <i class="glyphicon glyphicon-pencil"></i> Manage Category
                        <span class="caret pull-right"></span>
                    </a>
                    <!-- Sub menu -->
                    <ul>
                        <li><a href="<c:url value="#"/>"><i class="glyphicon glyphicon-plus"></i> Add category</a></li>
                        <li><a href="<c:url value="#"/>"><i class="glyphicon glyphicon-repeat"></i> Update category</a></li>
                    </ul>
                </li>
                <li class="submenu">
                    <a href="#">
                        <i class="glyphicon glyphicon-pencil"></i> Manage order
                        <span class="caret pull-right"></span>
                    </a>
                    <ul>
                        <li><a href="<c:url value="../seller/processing-orders"/>"><i class="glyphicon glyphicon-plus"></i> New orders</a></li>
                        <li><a href="<c:url value="../seller/shipping-orders"/>"><i class="glyphicon glyphicon-road"></i> Shipping Orders</a></li>
                        <li><a href="<c:url value="../seller/paid-orders"/>"><i class="glyphicon glyphicon-ok-circle"></i> Paid Orders</a></li>
                    </ul>
                </li>
                <li><a href="#"><i class="glyphicon glyphicon-list"></i> Make order</a></li>
                <li><a href="#"><i class="glyphicon glyphicon-tasks"></i> Make report</a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_SELLER')">
                <li><a href="#"><i class="glyphicon glyphicon-pencil"></i> Manage order</a></li>
                <li><a href="#"><i class="glyphicon glyphicon-list"></i> Make order</a></li>
                <li><a href="#"><i class="glyphicon glyphicon-tasks"></i> Make report</a></li>
                </sec:authorize>
<!--            <li><a href="calendar.html"><i class="glyphicon glyphicon-calendar"></i> Calendar</a></li>
            <li><a href="stats.html"><i class="glyphicon glyphicon-stats"></i> Statistics (Charts)</a></li>
            <li><a href="tables.html"><i class="glyphicon glyphicon-list"></i> Tables</a></li>
            <li><a href="buttons.html"><i class="glyphicon glyphicon-record"></i> Buttons</a></li>
            <li><a href="editors.html"><i class="glyphicon glyphicon-pencil"></i> Editors</a></li>
            <li><a href="forms.html"><i class="glyphicon glyphicon-tasks"></i> Forms</a></li>

            <li class="submenu">
                <a href="#">
                    <i class="glyphicon glyphicon-list"></i> Pages
                    <span class="caret pull-right"></span>
                </a>
                 Sub menu 
                <ul>
                    <li><a href="login.html">Login</a></li>
                    <li><a href="signup.html">Signup</a></li>
                </ul>
            </li>-->
        </ul>
    </div>
</div>