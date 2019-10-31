<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  uri="http://www.springframework.org/security/tags" prefix="sec" %>
<footer>
    <div class="container">

        <div class="copy text-center">
            Copyright 2019
        </div>

    </div>
</footer>
<!-- DataTables CSS -->
<link rel="stylesheet" type="text/css" href="<c:url value="/resources-management/DataTables-1.10.20/media/css/jquery.dataTables.css"/>">
<!-- jQuery -->
<script type="text/javascript" charset="utf8" src="<c:url value="/resources-management/DataTables-1.10.20/media/js/jquery.js"/>"></script>
<!-- DataTables -->
<script type="text/javascript" charset="utf8" src="<c:url value="/resources-management/DataTables-1.10.20/media/js/jquery.dataTables.js"/>"></script>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!--<script src="https://code.jquery.com/jquery.js"></script>-->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<c:url value="/resources-management/bootstrap/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources-management/js/custom.js"/>"></script>