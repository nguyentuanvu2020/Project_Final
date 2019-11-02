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
                <div class="col-md-9">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="content-box-large">
                                <div class="panel-heading">
                                    <div class="panel-title"><!--Order Detail--></div>
                                </div>
                                <div class="panel-body">
                                    <div class="col-md-12">
                                        <div class="row" style="text-align: center;">
                                            <h2>Order Detail</h2>
                                        </div>
                                        <fieldset>
                                            <legend>&nbsp;</legend>
                                            <div class="row">
                                                <div class="col-md-4" style="center;">
                                                    <div class="col-md-12" style="text-align: left;">
                                                        <div class="col-lg-6">
                                                            <label class="l">Oder number :</label>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <p class="s">123001</p>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <label class="l">Order date :</label>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <p class="s">2019-10-10</p>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <label class="l">Order status :</label>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <p class="s">PROCESSING</p>
                                                        </div>
                                                    </div>

                                                </div>
                                                <div class="col-md-8" style="">
                                                    <div class="col-md-12" style="text-align: left;">
                                                        <div class="col-lg-4">
                                                            <label class="l">Name :</label>
                                                        </div>
                                                        <div class="col-lg-8">
                                                            <p class="s">Phan Dinh Hiep</p>
                                                        </div>
                                                        <div class="col-lg-4">
                                                            <label class="l">Phone number :</label>
                                                        </div>
                                                        <div class="col-lg-8">
                                                            <p class="s">0935946258</p>
                                                        </div>
                                                        <div class="col-lg-4">
                                                            <label class="l">Email address :</label>
                                                        </div>
                                                        <div class="col-lg-8">
                                                            <p class="s">phandinhhiep94@gmail.com</p>
                                                        </div>
                                                        <div class="col-lg-4">
                                                            <label class="l">Address :</label>
                                                        </div>
                                                        <div class="col-lg-8">
                                                            <p>79 Quang Trung, Hai Chau, TP Da Nang</p>
                                                        </div>
                                                        <div class="col-lg-4">
                                                            <label class="l">Note :</label>
                                                        </div>
                                                        <div class="col-lg-8">
                                                            <p>Trung tam dao tao lap trinh vien chuyen nghiep Iviettech</p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12" style="text-align: left;">
                                                    <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example-test">
                                                        <thead>
                                                            <tr>
                                                                <th>#</th>
                                                                <th>Product Id</th>
                                                                <th>Product Name</th>
                                                                <th>Size</th>
                                                                <th>Color</th>
                                                                <th>Quantity</th>
                                                                <th>UnitPrice</th>
                                                                <th>Price</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:set var="i" value="1"/>
                                                            <c:forEach var="item" items="${orderDetail.listOrderDetail}">
                                                                <tr class="odd gradeX">
                                                                    <td>${i}</td>
                                                                    <td>${item.product.id}</td>
                                                                    <td>${item.product.name}</td>
                                                                    <td>${item.size}</td>
                                                                    <td>${item.color}</td>
                                                                    <td>${item.quantity}</td>
                                                                    <td><fmt:formatNumber minFractionDigits="0" type="number" value="${item.unitPrice}"/></td>
                                                                    <td><fmt:formatNumber minFractionDigits="0" type="number" value="${item.price}"/></td>
                                                                </tr>
                                                                <c:set var="i" value="${i+1}"/>
                                                            </c:forEach>
                                                            <tr class="odd gradeX">
                                                                <td colspan="7">
                                                                    <p class="ss txt-center">Total:</p>
                                                                </td>
                                                                <td>
                                                                    <p class="ss"><fmt:formatNumber minFractionDigits="0" type="number" value="${orderDetail.totalPrice}"/></p>
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </fieldset>
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
        <style>
            .l{
                text-decoration: underline;
            }
            .s{
                font-size: 12px;
            }
            .ss{
                font-size: 16px;
            }
            .txt-center{
                text-align: center;
            }
        </style>
        <!--include footer-->
        <%@include file="../../include-management/footer.jsp" %>
    </body>
</html>
