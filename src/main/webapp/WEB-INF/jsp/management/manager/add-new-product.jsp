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
                        <div class="col-md-10">
                            <div class="content-box-large">
                                <div class="panel-heading">
                                    <div class="panel-title">Add new product</div>

                                    <div class="panel-options">
                                        <a href="#" data-rel="collapse"><i class="glyphicon glyphicon-refresh"></i></a>
                                        <a href="#" data-rel="reload"><i class="glyphicon glyphicon-cog"></i></a>
                                    </div>
                                </div>
                                <div class="panel-body">
                                    <f:form method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/${action}" 
                                            modelAttribute="product">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Product name</label>
                                                <input class="form-control" name="name" value="${product.name}" type="text">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Price</label>
                                                <input class="form-control" name="price" type="number" value="${product.price}">
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Description</label>
                                                <textarea class="form-control" name="description" rows="5" id="des">${product.description}</textarea>
                                            </div>
                                        </div>
                                        <div class="col-md-6">    
                                            <div class="form-group">
                                                <label>Category</label>
                                                <select class="form-control" name="category.id" id="select-1">
                                                    <c:forEach var="category" items="${categorys}">
                                                        <option value="${category.id}">${category.name}</option>
                                                    </c:forEach>
                                                </select> 
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Mumber of images</label>
                                                <input type="number" min="0" required="" id="number-of-image" onchange="addElementImage()" class="form-control"/>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="form-group" id="images">
                                                <!--content will auto display-->
                                                <!--                                                <label>Image 1</label><br>
                                                                                                <input type="file" name="file" class="btn btn-default">
                                                                                                <label>Image 2</label><br>
                                                                                                <input type="file" name="file" class="btn btn-default">-->
                                            </div>
                                        </div>

                                        <label>Color - Size - Quantity</label>
                                        <div class="form-group">
                                            <div class="form-group col-sm-4">
                                                <!--<input type="email" class="form-control" id="exampleInputEmail2" placeholder="Enter email">-->
                                                <label>Color</label>
                                                <select class="form-control" id="color">
                                                    <c:forEach var="color" items="${colors}">
                                                        <option style="background-color: ${color.productColor};" value="${color.id}">${color.productColor}</option>
                                                    </c:forEach>
                                                </select> 
                                            </div>
                                            <div class="form-group col-sm-4">
                                                <!--<input type="password" class="form-control" id="exampleInputPassword2" placeholder="Password">-->
                                                <label>Size</label>
                                                <select class="form-control" id="size">
                                                    <c:forEach var="size" items="${sizes}">
                                                        <option value="${size.id}">${size.productSize}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group col-sm-2">
                                                <label>Quantity</label>
                                                <input type="number"  min="0" class="form-control" id="quantity">             
                                            </div>
                                            <div class="form-group col-sm-2">
                                                <label>&nbsp;</label><br>
                                                <button type="button" class='btn btn-primary' id="add-detail" onclick="addDetail()">
                                                    Add
                                                </button>
                                            </div>
                                        </div>
                                        <div id="info" style="display: none;" class="alert alert-danger">
                                            <p id="returntext">Delete detail please!</p>
                                        </div>
                                        <div class="col-md-12">
                                            <legend>List detail</legend>
                                            <table id="myTable" class="table table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>#</th>
                                                        <th>Color</th>
                                                        <th>Size</th>
                                                        <th>Quantity</th>
                                                        <th>Option</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="row-data">

                                                </tbody>
                                            </table>
                                            <div>
                                                <div style="text-align: center;">
                                                    <button class="btn btn-primary" type="submit">Confirm</button>
                                                    <button class="btn btn-primary" type="reset" onclick="location.href = '<c:url value="../../management/"/>'">
                                                        Cancel
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </f:form>
                                </div></div>
                        </div>
                    </div>

                    <!--                        <div class="col-md-4">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="content-box-header">
                                                            <div class="panel-title">New vs Returning Visitors</div>
                    
                                                            <div class="panel-options">
                                                                <a href="#" data-rel="collapse"><i class="glyphicon glyphicon-refresh"></i></a>
                                                                <a href="#" data-rel="reload"><i class="glyphicon glyphicon-cog"></i></a>
                                                            </div>
                                                        </div>
                                                        <div class="content-box-large box-with-header">
                    
                                                            Pellentesque luctus quam quis consequat vulputate. Sed sit amet diam ipsum. Praesent in pellentesque diam, sit amet dignissim erat. Aliquam erat volutpat. Aenean laoreet metus leo, laoreet feugiat enim suscipit quis. Praesent mauris mauris, ornare vitae tincidunt sed, hendrerit eget augue. Nam nec vestibulum nisi, eu dignissim nulla.
                                                            <br /><br />
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="content-box-header">
                                                            <div class="panel-title">New vs Returning Visitors</div>
                    
                                                            <div class="panel-options">
                                                                <a href="#" data-rel="collapse"><i class="glyphicon glyphicon-refresh"></i></a>
                                                                <a href="#" data-rel="reload"><i class="glyphicon glyphicon-cog"></i></a>
                                                            </div>
                                                        </div>
                                                        <div class="content-box-large box-with-header">
                    
                                                            Pellentesque luctus quam quis consequat vulputate. Sed sit amet diam ipsum. Praesent in pellentesque diam, sit amet dignissim erat. Aliquam erat volutpat. Aenean laoreet metus leo, laoreet feugiat enim suscipit quis. Praesent mauris mauris, ornare vitae tincidunt sed, hendrerit eget augue. Nam nec vestibulum nisi, eu dignissim nulla.
                                                            <br /><br />
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>-->
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
