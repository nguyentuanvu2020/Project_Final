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
                                    <div class="${style}">
                                        ${message}
                                    </div>
                                    <f:form method="POST" action="${pageContext.request.contextPath}/${action}" 
                                            modelAttribute="category">
                                        <c:if test="${action =='management/manager/edit-category'}">
                                            <input name="id" value="${category.id}" hidden=""/>
                                        </c:if>
                                        <div class="form-group">
                                            <label>Category name</label>
                                            <input class="form-control" name="name" type="text" value="${category.name}">
                                        </div>
                                        <div class="form-group">
                                            <label>Description</label>
                                            <textarea class="form-control" name="description" rows="3" id="des">${category.description}</textarea>
                                        </div>
                                        <div>
                                            <div style="text-align: center;">
                                                <button class="btn btn-primary" type="submit">Confirm</button>
                                                <button class="btn btn-primary" type="reset" onclick="location.href = '<c:url value="../../management/"/>'">
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
        <!--include footer-->
        <%@include file="../../include-management/footer.jsp" %>
    </body>
</html>

