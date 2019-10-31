<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
    <title>Management</title>
    <link href="https://code.jquery.com/ui/1.10.3/themes/redmond/jquery-ui.css" rel="stylesheet" media="screen">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="<c:url value="/resources-management/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">
    <!-- styles -->
    <link href="<c:url value="/resources-management/css/styles.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources-management/css/buttons.css"/>" rel="stylesheet">
    <script type="text/javascript" charset="utf8" src="<c:url value="/resources-management/DataTables-1.10.20/media/js/jquery.js"/>"></script>
    <script src="<c:url value="/resources-management/js/scriptshop.js"/>"></script>
</head>
