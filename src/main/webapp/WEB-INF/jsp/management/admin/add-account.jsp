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
                        <div class="col-md-9">
                            <div class="content-box-large">
                                <div class="panel-heading">
                                    <div class="panel-title">New Account</div>

                                    <div class="panel-options">
                                        <a href="#" data-rel="collapse"><i class="glyphicon glyphicon-refresh"></i></a>
                                        <a href="#" data-rel="reload"><i class="glyphicon glyphicon-cog"></i></a>
                                    </div>
                                </div>
                                <div class="panel-body">
                                    <f:form modelAttribute="newAccount" method="post" action="${pageContext.request.contextPath}/admin/${action}">
                                        <c:if test="${action=='edit'}">
                                            <input class="form-control" value="${newAccount.id}" name="id" type="number">
                                        </c:if>
                                        <fieldset>
                                            <legend>Account info</legend>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Name</label>
                                                    <input class="form-control" value="${newAccount.name}" name="name" type="text">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Birth date</label>
                                                    <input class="form-control" value="${newAccount.birthDate}" name="birthDate" type="date">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Phone number</label>
                                                    <input class="form-control" value="${newAccount.phoneNumber}" name="phoneNumber" type="number">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Email address</label>
                                                    <input class="form-control" value="${newAccount.email}" name="email" type="email">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label">ROLE</label>
                                                    <div class="col-md-12">
                                                        <c:forEach var="role" items="${roles}">
                                                            <label class="checkbox-inline">
                                                                <input name="role" value="${role.id}" type="checkbox">
                                                                ${role.role} </label><br>
                                                            </c:forEach>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-12">
                                                <div class="form-group">
                                                    <label>Address</label>
                                                    <textarea name="address" class="form-control" placeholder="Address" rows="5">${newAccount.adress}</textarea>
                                                </div>
                                            </div>
                                        </fieldset>
                                        <div class="col-md-12" style="text-align: center;">
                                            <input type="reset" value="Cancel">
                                            <input type="submit" value="Add">
                                        </div>
                                    </f:form>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-3">
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
