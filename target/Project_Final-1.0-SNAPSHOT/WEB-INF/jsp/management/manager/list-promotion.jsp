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
                                    <div class="panel-title">List promotion</div>
                                </div>
                                <div class="panel-body">
                                    <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>ID</th>
                                                <th>Description</th>
                                                <th>Start date</th>
                                                <th>End date</th>
                                                <th>Discount</th>
                                                <th>Status</th>
                                                <th>Image</th>
                                                <th>Options</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:set var="i" value="1"/>
                                            <c:forEach var="promotion" items="${promotions}">
                                                <tr class="odd gradeX">
                                                    <td>${i}</td>
                                                    <td>${promotion.id}</td>
                                                    <td class="mouser-hover" onclick="location.href='<c:url value="detail-promotion/${promotion.id}"/>'">${promotion.description}</td>
                                                    <td>${promotion.startDate}</td>
                                                    <td>${promotion.endDate}</td>
                                                    <td>${promotion.discount}%</td>
                                                    <td>${promotion.status}</td>
                                                    <td><img height="50" width="50px" src="<c:url value="/resources/image/${promotion.image}"/>"></td>
                                                    <td>
                                                        <button class="btn btn-primary btn-xs" onclick="location.href = '<c:url value="update-promotion/${promotion.id}"/>'">
                                                            <i class="glyphicon glyphicon-refresh"></i> 
                                                            Update
                                                        </button>
                                                        <button class="btn btn-primary btn-xs" onclick="location.href = '<c:url value="add-product-promotion/${promotion.id}"/>'">
                                                            <i class="glyphicon glyphicon-plus"></i> 
                                                            Add product
                                                        </button>   
                                                        <button class="btn btn-danger  btn-xs">
                                                            <i class="glyphicon glyphicon-remove"></i>
                                                            Delete
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
