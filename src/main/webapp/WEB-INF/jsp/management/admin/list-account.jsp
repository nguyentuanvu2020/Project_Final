<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  uri="http://www.springframework.org/security/tags" prefix="sec" %>
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
                                    <div class="panel-title">New vs Returning Visitors</div>

                                    <div class="panel-options">
                                        <a href="#" data-rel="collapse"><i class="glyphicon glyphicon-refresh"></i></a>
                                        <a href="#" data-rel="reload"><i class="glyphicon glyphicon-cog"></i></a>
                                    </div>
                                </div>
                                <div class="panel-body">
                                    ${info}
                                    <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>ID</th>
                                                <th>Name</th>
                                                <th>Gender</th>
                                                <th>Birth date</th>
                                                <th>Phone</th>
                                                <th>Email</th>
                                                <th>Address</th>
                                                <th>Disabled</th>
                                                <th>Options</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:set var="i" value="1"/>
                                            <c:forEach var="ac" items="${listAccount}">
                                                <tr class="odd gradeX">
                                                    <td>${i}</td>
                                                    <td>${ac.id}</td>
                                                    <td>${ac.name}</td>
                                                    <td>${ac.gender}</td>
                                                    <td>${ac.birthDate}</td>
                                                    <td>${ac.phoneNumber}</td>
                                                    <td>${ac.email}</td>
                                                    <td>${ac.address}</td>
                                                    <td>
                                                        ${ac.disabled}
                                                        <c:choose>
                                                            <c:when test="${ac.disabled=='true'}">

                                                                <button class="btn btn-success btn-xs" onclick="location.href = '<c:url value="${request.contextPath}/admin/update-status/${ac.id}'"/>">
                                                                    <i class="glyphicon glyphicon-ok"></i>
                                                                    Active
                                                                </button>
                                                            </c:when>
                                                            <c:when test="${ac.disabled=='false'}">
                                                                <button class="btn btn-danger  btn-xs" onclick="location.href = '<c:url value="${request.contextPath}/admin/update-status/${ac.id}'"/>">
                                                                    <i class="glyphicon glyphicon-remove"></i>
                                                                    Block
                                                                </button>
                                                            </c:when>
                                                        </c:choose>
                                                    </td>
                                                    <td>
                                                        <button class="btn btn-primary  btn-xs" onclick="location.href = '<c:url value="${request.contextPath}/management/profile/${ac.id}'"/>">
                                                            <i class="glyphicon glyphicon-eye-open"></i>
                                                            Detail
                                                        </button>
                                                    </td>
                                                </tr>
                                                <c:set var="i" value="${i+1}"/>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
<!--                                <div class="row">
                                    <div class="col-md-3" style="background-color: red; height: 100px;">
                                        
                                    </div>
                                    <div class="col-md-3" style="background-color: blue; height: 100px;">

                                    </div>
                                    <div class="col-md-3" style="background-color: green; height: 100px;">
                                       
                                    </div>
                                    <div class="col-md-3" style="background-color: navy; height: 100px;">

                                    </div>
                                </div>-->
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
