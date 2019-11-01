<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
    <!--include headercss-->
    <%@include file="../include-management/headcss.jsp" %>
    <body>
        <!--include header--> 
        <%@include file="../include-management/header.jsp" %>
        <div class="page-content">
            <div class="row">
                <!--include menu-->
                <%@include file="../include-management/menumanagement.jsp" %>
                <div class="col-md-9">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="content-box-large">
                                <div class="panel-heading">
                                    <div class="panel-title">Welcome</div>
                                </div>
                                <div class="row">
                                </div>
                                <div class="row">
                                    <ul class="bs-glyphicons">
                                        <a href="<c:url value="seller/processing-orders"/>">
                                            <li>
                                                <span class="glyphicon glyphicon-list-alt"></span>
                                                <span class="glyphicon-class">New order</span>
                                            </li>
                                        </a>
                                        <a href="<c:url value="seller/list-order-processing"/>">
                                            <li>
                                                <span class="glyphicon glyphicon-ok-sign"></span>
                                                <h1 class="glyphicon-class">Order paid</h1>
                                                <span class="glyphicon-class">Order paid</span>
                                            </li>
                                        </a>
                                        <a href="#">
                                            <li>
                                                <span class="glyphicon glyphicon-send"></span>
                                                <h1 class="glyphicon-class">Order send</h1>
                                                <span class="glyphicon-class">Order send</span>
                                            </li>
                                        </a>
                                        <a href="#">
                                            <li>
                                                <span class="glyphicon glyphicon-usd"></span>
                                                <h1 class="glyphicon-class">Total amount</h1>
                                                <span class="glyphicon-class">Total amount</span>
                                            </li>
                                        </a>
                                    </ul>
                                </div>
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
    <%@include file="../include-management/footer.jsp" %>
</body>
</html>