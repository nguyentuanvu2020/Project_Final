<%-- 
    Document   : customer-form
    Created on : Aug 27, 2019, 8:45:39 PM
    Author     : motvo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer-Form Page</title>
        <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/3.4.1/css/bootstrap.min.css"/>"/>
    </head>
    <body>
        <div class="jumbotron"></div>
        <div class="container">
            <div class="well col-sm-8">
                <h1>${title}</h1>
            </div>
            <div class="well col-sm-8">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="category">Color</label>
                    <div class="col-sm-10">
                        <select name="color" id="color" class="form-control">
                            <c:forEach var="color" items="${colors}">
                                <option value="${color}" name="category">${color}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="category">Size</label>
                    <div class="col-sm-10">
                        <select name="size" id="size" class="form-control">
                            <c:forEach var="size" items="${sizes}">
                                <option value="${size.getSize()}" name="category">${size.getSize()}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="quantity">Quantity</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="quantity" placeholder="Quantity" name="quantity" value="${book.name}">
                    </div>
                </div>

            </div>
            <div class="form-group"> 
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" class="btn btn-default" onclick="myFunction()">Submit</button>
                </div>
            </div>
        </div>
        <script>
            function myFunction() {
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("zzz").innerHTML = "vu";
                    }
                };
                xhttp.open("GET", "update-productdetail", true);
                xhttp.send();
            }
        </script>
        <div class="jumbotron" id="zzz">

        </div>
    </body>
</html>
