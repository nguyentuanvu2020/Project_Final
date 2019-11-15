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
                                    <div class="panel-title">Update promotion</div>
                                    <div class="panel-options">
                                        <a href="#" data-rel="collapse"><i class="glyphicon glyphicon-refresh"></i></a>
                                        <a href="#" data-rel="reload"><i class="glyphicon glyphicon-cog"></i></a>
                                    </div>
                                </div>
                                <div class="panel-body">
                                    <f:form method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/${action}" 
                                            modelAttribute="newPromotion">
                                        <c:if test="${check =='edit'}">
                                            <input class="form-control" name="id" value="${newPromotion.id}" type="hidden">
                                            <input class="form-control" name="image" value="${newPromotion.image}" type="hidden">
                                        </c:if>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Discount</label>
                                                <input class="form-control" name="discount" type="number"  value="${newPromotion.discount}">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Start date</label>
                                                <input class="form-control" id="start-date" name="startDate" type="date" value="${newPromotion.startDate}">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>End date</label>
                                                <input class="form-control" id="end-date" name="endDate" type="date" value="${newPromotion.endDate}">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>Description</label>
                                            <textarea class="form-control" name="description" rows="5" >${newPromotion.description}</textarea>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="col-md-4">
                                                <img id="image" src="<c:url value="/resources/image/${newPromotion.image}"/>" alt="..."  class="img-thumbnail" style="height: 150px; width: 200px">
                                                <br>
                                                <button type="button" class="btn btn-danger btn-xs" onclick="display()">Delete</button>
                                            </div>
                                        </div>
                                        <c:if test="${newPromotion.image == null}">
                                            <div id="div-file" class="form-group" style="">
                                                <label>Image</label>
                                                <input class='form-control' name='file' type='file'>
                                            </div>
                                        </c:if>
                                        <div id="div-file" class="form-group" style="display: none;">
                                            <label>Image</label>
                                            <input class='form-control' name='file' type='file'>
                                        </div>

                                        <div>
                                            <div style="text-align: center;">
                                                <button class="btn btn-primary" type="submit">Confirm</button>
                                                <button class="btn btn-primary" type="reset" onclick="location.href = '<c:url value="${request.contextPath}/management/"/>'">
                                                    Cancel
                                                </button>
                                            </div>
                                        </div>
                                    </f:form>
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
        <script>
            function display() {
                document.getElementById("image").src = "${pageContext.request.contextPath}/resources/image/noneimage.jpg";
                document.getElementById("div-file").style.display = "block";
            }
        </script>
        <!--include footer-->
        <%@include file="../../include-management/footer.jsp" %>
    </body>
</html>
