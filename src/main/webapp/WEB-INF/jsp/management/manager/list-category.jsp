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
                                    <div class="panel-title">List categories</div>
                                </div>
                                <div class="panel-body">
                                    <div class="${style}">
                                        ${message}
                                    </div>
                                    <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>ID</th>
                                                <th>Category Name</th>
                                                <th>Description</th>
                                                <th>Options</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:set var="i" value="1"/>
                                            <c:forEach var="caregory" items="${caregorys}">
                                                <tr class="odd gradeX">
                                                    <td>${i}</td>
                                                    <td>${caregory.id}</td>
                                                    <td class="center">${caregory.name}</td>
                                                    <td class="center">${caregory.description}</td>
                                                    <td class="center">
                                                        <button class="btn btn-primary btn-xs" onclick="location.href='<c:url value="edit-category/${caregory.id}"/>'">
                                                            <i class="glyphicon glyphicon-refresh"></i> 
                                                            Update
                                                        </button>
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
