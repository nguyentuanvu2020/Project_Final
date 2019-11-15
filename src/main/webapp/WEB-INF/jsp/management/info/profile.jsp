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
                                    <div class="panel-title">Account Info</div>
                                    
                                    <div class="panel-options">
                                        <a href="#" data-rel="collapse"><i class="glyphicon glyphicon-refresh"></i></a>
                                        <a href="#" data-rel="reload"><i class="glyphicon glyphicon-cog"></i></a>
                                    </div>
                                </div>
                                <div class="panel-body">
                                    ${info}
                                    <f:form modelAttribute="account" method="post" action="${pageContext.request.contextPath}/management/${action}">
                                        <c:if test="${action=='update-profile'}">
                                            <input class="form-control" value="${account.id}" name="id" type="hidden">
                                        </c:if>
                                        <fieldset>

                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Name</label>
                                                    <input class="form-control" value="${account.name}" name="name" type="text">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Birth date</label>
                                                    <input class="form-control" value="${account.birthDate}" name="birthDate" type="date">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Phone number</label>
                                                    <input class="form-control" value="${account.phoneNumber}" name="phoneNumber" type="number">
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Email address</label>
                                                    <p class="form-control">${account.email}</p>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label">ROLE</label>
                                                    <div class="col-md-12">
                                                        <c:set var="check" value="${false}"/>
                                                        <c:forEach var="role" items="${roles}">
                                                            <label class="checkbox-inline">
                                                                <input name="role" value="${role.id}" 
                                                                       <c:forEach var="acrole" items="${account.accountRoles}">
                                                                           <c:if test="${role.id == acrole.id}">checked=""</c:if>
                                                                       </c:forEach> type="checkbox">
                                                                ${role.role}</label><br>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-12">
                                                <div class="form-group">
                                                    <label>Address</label>
                                                    <textarea name="address" class="form-control" placeholder="Address" rows="5">${account.address}</textarea>
                                                </div>
                                            </div>
                                        </fieldset>
                                        <div class="col-md-12" style="text-align: center;">
                                            <input class="btn btn-default btn-primary" type="reset" value="Cancel">
                                            <input class="btn btn-default btn-primary" type="submit"  value="Update">
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
