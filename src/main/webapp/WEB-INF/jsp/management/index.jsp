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
                                        <li>
                                            <span class="glyphicon glyphicon-adjust"></span>
                                            <span class="glyphicon-class">glyphicon glyphicon-adjust</span>
                                        </li>
                                        <li>
                                            <span class="glyphicon glyphicon-align-center"></span>
                                            <span class="glyphicon-class">glyphicon glyphicon-align-center</span>
                                        </li>
                                        <li>
                                            <span class="glyphicon glyphicon-align-justify"></span>
                                            <span class="glyphicon-class">glyphicon glyphicon-align-justify</span>
                                        </li>
                                        <li>
                                            <span class="glyphicon glyphicon-align-justify"></span>
                                            <span class="glyphicon-class">glyphicon glyphicon-align-justify</span>
                                        </li>
                                    </ul>
                                </div>
                                <!--                                    <div class="col-md-3 col-sm-6">
                                                                        <div class="a">
                                
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-3 col-sm-6">
                                                                        <div class="a">
                                
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-3 col-sm-6">
                                                                        <div class="a">
                                
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-3 col-sm-6">
                                                                        <div class="a">
                                
                                                                        </div>
                                                                    </div>-->
                                <style>
                                    .c{
                                        border-radius: 10%;
                                    }
                                    .a{
                                        height: 285px;
                                        background-color: #008A7B;
                                        border-radius: 10% ;
                                        margin-top: 5%;
                                    }
                                </style>
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