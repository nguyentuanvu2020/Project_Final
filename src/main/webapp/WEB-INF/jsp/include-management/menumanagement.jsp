<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  uri="http://www.springframework.org/security/tags" prefix="sec" %>
<div class="col-md-2">
    <div class="sidebar content-box" style="display: block;">
        <ul class="nav">
            <!-- Main menu -->
            <li class="current"><a href="<c:url value="/management/"/>"><i class="glyphicon glyphicon-home"></i> Dashboard</a></li>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li class="submenu">
                    <a href="#">
                        <i class="glyphicon glyphicon-list"></i> Account
                        <span class="caret pull-right"></span>
                    </a>
                    <!-- Sub menu -->
                    <ul>
                        <li><a href="<c:url value="${request.contextPath}/admin/add-account"/>"><i class="glyphicon glyphicon-plus"></i> Add account</a></li>
                        <li><a href="<c:url value="${request.contextPath}/admin/list-account"/>"><i class="glyphicon glyphicon-list"></i> List account</a></li>
                    </ul>
                </li>
            </sec:authorize>

            <sec:authorize access="hasRole('ROLE_MANAGER')">
                <li class="submenu">
                    <a href="#">
                        <i class="glyphicon glyphicon-pencil"></i> Product
                        <span class="caret pull-right"></span>
                    </a>
                    <!-- Sub menu -->
                    <ul>
                        <li><a href="<c:url value="${request.contextPath}/management/manager/add-new-product"/>"><i class="glyphicon glyphicon-plus"></i> Add product</a></li>
                        <li><a href="<c:url value="${request.contextPath}/management/manager/list-product"/>"><i class="glyphicon glyphicon-list"></i> List product</a></li>
                    </ul>
                </li>
                <li class="submenu">
                    <a href="#">
                        <i class="glyphicon glyphicon-pencil"></i> Promotion
                        <span class="caret pull-right"></span>
                    </a>
                    <!-- Sub menu -->
                    <ul>
                        <li><a href="<c:url value="${request.contextPath}/management/manager/add-promotion"/>"><i class="glyphicon glyphicon-plus"></i> Add promotion</a></li>
                        <li><a href="<c:url value="${request.contextPath}/management/manager/list-promotion"/>"><i class="glyphicon glyphicon-repeat"></i> List promotion</a></li>
                    </ul>
                </li>
                <li class="submenu">
                    <a href="#">
                        <i class="glyphicon glyphicon-pencil"></i> Category
                        <span class="caret pull-right"></span>
                    </a>
                    <!-- Sub menu -->
                    <ul>
                        <li><a href="<c:url value="${request.contextPath}/management/manager/add-new-category"/>"><i class="glyphicon glyphicon-plus"></i> Add category</a></li>
                        <li><a href="<c:url value="${request.contextPath}/management/manager/list-category"/>"><i class="glyphicon glyphicon-repeat"></i> List categories</a></li>
                    </ul>
                </li>
<!--                <li class="submenu">
                    <a href="#">
                        <i class="glyphicon glyphicon-pencil"></i> Order
                        <span class="caret pull-right"></span>
                    </a>
                    <ul>
                        <li><a href="<c:url value="${request.contextPath}/management/seller/processing-orders"/>"><i class="glyphicon glyphicon-asterisk"></i> New orders</a></li>
                        <li><a href="<c:url value="${request.contextPath}/management/seller/shipping-orders"/>"><i class="glyphicon glyphicon-road"></i> Shipping Orders</a></li>
                        <li><a href="<c:url value="${request.contextPath}/management/seller/paid-orders"/>"><i class="glyphicon glyphicon-ok-circle"></i> Paid Orders</a></li>
                        <li><a href="<c:url value="${request.contextPath}/management/seller/cancel-orders"/>"><i class="glyphicon glyphicon-remove-sign"></i> Cancel Orders</a></li>
                    </ul>
                </li>-->
<!--                <li><a href="<c:url value="${request.contextPath}/management/seller/makeorder"/>"><i class="glyphicon glyphicon-list"></i> Make order</a></li>-->
                <!--<li><a href="<c:url value="${request.contextPath}/management/seller/make-report"/>"><i class="glyphicon glyphicon-tasks"></i> Make report</a></li>-->
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_SELLER')">
                <!--<li><a href="#"><i class="glyphicon glyphicon-list"></i> Make order</a></li>-->
                <!--<li><a href="#"><i class="glyphicon glyphicon-tasks"></i> Make report</a></li>-->
                <li class="submenu">
                    <a href="#">
                        <i class="glyphicon glyphicon-pencil"></i> Order
                        <span class="caret pull-right"></span>
                    </a>
                    <ul>
                        <li><a href="<c:url value="${request.contextPath}/management/seller/processing-orders"/>"><i class="glyphicon glyphicon-asterisk"></i> New orders</a></li>
                        <li><a href="<c:url value="${request.contextPath}/management/seller/shipping-orders"/>"><i class="glyphicon glyphicon-road"></i> Shipping Orders</a></li>
                        <li><a href="<c:url value="${request.contextPath}/management/seller/paid-orders"/>"><i class="glyphicon glyphicon-ok-circle"></i> Paid Orders</a></li>
                        <li><a href="<c:url value="${request.contextPath}/management/seller/cancel-orders"/>"><i class="glyphicon glyphicon-remove-sign"></i> Cancel Orders</a></li>
                    </ul>
                </li>
            </sec:authorize>

            <!-- <li><a href="calendar.html"><i class="glyphicon glyphicon-calendar"></i> Calendar</a></li>
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