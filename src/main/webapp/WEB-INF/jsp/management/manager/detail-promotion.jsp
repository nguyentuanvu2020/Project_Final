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
                <%@include file="../../include-management/menumanagement.jsp" %>
                <div class="col-md-10">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="content-box-large">
                                <div class="panel-heading">
                                    <div class="panel-title">Select product</div>
                                </div>
                                <div class="panel-body">
                                        <div class="form-group">
                                            <input class="form-control" name="promotionid" type="hidden"  value="${promotion.id}">
                                            <label>Discount</label>
                                            <p class="form-control">${promotion.discount}%</p>
                                        </div>
                                        <div class="form-group">
                                            <label>Description</label>
                                            <p class="form-control">${promotion.description}</p>
                                        </div>
                                        <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>ID</th>
                                                    <th>Name</th>
                                                    <th>Price</th> 
                                                    <th>Description</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:set var="i" value="1"/>
                                                <c:forEach var="product" items="${promotion.listProduct}">
                                                    <tr class="odd gradeX">
                                                        <td>${i}</td>
                                                        <td>${product.id}</td>
                                                        <td>${product.name}</td>
                                                        <td class="center"><fmt:formatNumber minFractionDigits="0" type="number" value="${product.price}"/></td>
                                                        <td class="center">${product.description}</td>
                                                       
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
