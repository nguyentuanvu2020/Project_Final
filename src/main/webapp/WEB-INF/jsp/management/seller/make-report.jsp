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
                                    <div class="panel-title">Make Report</div>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-3" style="background-color: red;">

                                            <f:form action="#" method="post">
                                                <div class="form-group">
                                                    <label>Status</label>
                                                    <select class="form-control" name="status" id="">
                                                        <c:forEach var="ods" items="${oderStatus}">
                                                            <option value="${ods}">${ods}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <label>From</label>
                                                    <input type="date" class="form-control" id="exampleInputEmail2" placeholder="Enter email">
                                                </div>
                                                <div class="form-group">
                                                    <label>To</label>
                                                    <input type="date" class="form-control" id="exampleInputPassword2" placeholder="Password">
                                                </div>
                                                <div style="text-align: center;">
                                                    <button type="submit" class="btn btn-primary ">
                                                        Sign in
                                                    </button>
                                                </div>
                                            </f:form>

                                        </div>

                                        <div class="col-md-4" style="background-color: yellow;">
                                            <f:form action="#" method="post">
                                                <div class="form-group">
                                                    <label>Status</label>
                                                    <select class="form-control" name="status" id="">
                                                        <c:forEach var="ods" items="${oderStatus}">
                                                            <option value="${ods}">${ods}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <label>From</label>
                                                    <input type="date" class="form-control" id="exampleInputEmail2" placeholder="Enter email">
                                                </div>
                                                <div class="form-group">
                                                    <label>To</label>
                                                    <input type="date" class="form-control" id="exampleInputPassword2" placeholder="Password">
                                                </div>
                                                <div style="text-align: center;">
                                                    <button type="submit" class="btn btn-primary ">
                                                        Sign in
                                                    </button>
                                                </div>
                                            </f:form>
                                        </div>
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
        <%@include file="../../include-management/footer.jsp" %>

    </body>
</html>
