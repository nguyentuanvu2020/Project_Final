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
                                    <div class="panel-title">Make order</div>
                                </div>
                                <div class="panel-body">
                                    <fieldset>
                                        <legend>Customer info</legend>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Name</label>
                                                <input class="form-control" name="name" type="text">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Phone number</label>
                                                <input class="form-control" name="name" type="number">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Email address</label>
                                                <input class="form-control" name="name" type="email">
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Address</label>
                                                <textarea name="adress" class="form-control" placeholder="Textarea" rows="5"></textarea>
                                            </div>
                                        </div>
                                    </fieldset>
                                    <fieldset>
                                        <legend>Products</legend>
                                        <div class="col-md-12">
                                            <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th>#</th>
                                                        <th>Product name</th>
                                                        <th>Color</th>
                                                        <th>Size</th>
                                                        <th>Quantity</th>
                                                        <th>Unitprice</th>
                                                        <th>Promotion</th>
                                                        <th>Price</th>
                                                        <th>Option</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="data-row">

                                                </tbody>
                                            </table>
                                        </div>
                                    </fieldset>
                                    <h4>choice product</h4>
                                    <div class="col-md-12">
                                        <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>Product name</th>
                                                    <th>Color</th>
                                                    <th>Size</th>
                                                    <th>Quantity</th>
                                                    <th>Unitprice</th>
                                                   
                                                    <th>Option</th>
                                                </tr>
                                            </thead>
                                            <tbody>

                                                <c:forEach var="product" items="${products}" varStatus="i">
                                                    <tr class="odd gradeX">
                                                        <td>${i.count}</td>
                                                        <td>${product.name}</td>
                                                        <td>color</td>
                                                        <td>size</td>
                                                        <td style="width: 2%;">
                                                            <input type="number" min="1" style="width:100%;" value="1" class="btn btn-default btn-xs">
                                                        </td>
                                                        <td><fmt:formatNumber minFractionDigits="0" type="number" value="${product.price}"/></td>
                                                        <td>
                                                            <button class="btn btn-xs btn-warning" style="margin: 1px;">Add</button>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
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
