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
                                                <div class="col-md-4" >
                                                    <div class="col-md-12" style="text-align: left;">
                                                        <div class="col-lg-6">
                                                            <label class="l">Oder number :</label>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <p class="s">${orderDetail.id}</p>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <label class="l">Order date :</label>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <p class="s">${orderDetail.orderDate}</p>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <label class="l">Order status :</label>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <p class="s">${orderDetail.orderStatus}</p>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-8" style="">
                                                    <div class="col-md-12" style="text-align: left;">
                                                        <div class="col-lg-4">
                                                            <label class="l">Name :</label>
                                                        </div>
                                                        <div class="col-lg-8">
                                                            <p class="s">${orderDetail.customer.name}</p>
                                                        </div>
                                                        <div class="col-lg-4">
                                                            <label class="l">Phone number :</label>
                                                        </div>
                                                        <div class="col-lg-8">
                                                            <p class="s">${orderDetail.customer.phoneNumber}</p>
                                                        </div>
                                                        <div class="col-lg-4">
                                                            <label class="l">Email address :</label>
                                                        </div>
                                                        <div class="col-lg-8">
                                                            <p class="s">${orderDetail.customer.email}</p>
                                                        </div>
                                                        <div class="col-lg-4">
                                                            <label class="l">Address :</label>
                                                        </div>
                                                        <div class="col-lg-8">
                                                            <p>${orderDetail.customer.address}</p>
                                                        </div>
                                                        <div class="col-lg-4">
                                                            <label class="l">Note :</label>
                                                        </div>
                                                        <div class="col-lg-8">
                                                            <p>${orderDetail.note}</p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12 table-responsive" style="text-align: left;">
                                                    <table cellpadding="0" cellspacing="0" border="0" class="table table-responsive table-striped table-bordered" id="example-test">
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
                                                            <tr>
                                                                <td colspan="7">
                                                                    <p class="txt-center">Total:</p>
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
                                        <div style="text-align: center;">
                                            <button class="btn btn-sm btn-primary" onclick="javascript:history.back()">
                                                <i class="glyphicon glyphicon-arrow-left"></i>
                                                Back
                                            </button>
                                            <button onclick="location.href='<c:url value="${request.contextPath}/management/seller/export-file/${orderDetail.id}"/>'" class="btn btn-sm btn-primary">
                                                <i class="glyphicon glyphicon-print"></i>
                                                Print order
                                            </button>
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
                text-decoration: underline;
                font-size: 16px;
            }
        </style>
        <!--include footer-->
        <%@include file="../../include-management/footer.jsp" %>
    </body>
</html>
