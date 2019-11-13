<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
    <link rel="shortcut icon" href="//theme.hstatic.net/1000243581/1000361905/14/favicon.png?v=151" type="image/png" />
    <meta charset="utf-8" />
    <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css" href="/Project_Final/resources-management/DataTables-1.10.20/media/css/jquery.dataTables.css">
    <!-- jQuery -->
    <script type="text/javascript" charset="utf8" src="/Project_Final/resources-management/DataTables-1.10.20/media/js/jquery.js"></script>
    <!-- DataTables -->
    <script type="text/javascript" charset="utf8" src="/Project_Final/resources-management/DataTables-1.10.20/media/js/jquery.dataTables.js"></script>
    <script type="text/javascript" charset="utf8" src="/Project_Final/resources-management/DataTables-1.10.20/media/js/jquery.js"></script>
    <script src="/Project_Final/resources-management/bootstrap/js/bootstrap.min.js"></script>
    <script src="/Project_Final/resources-management/js/custom.js"></script>
    <title>TV SHOP</title>

</head>
<body class="hideresponsive">

    <table id="example">
        <tr>
            <td>ID</td>
            <td>Name</td>
        </tr>
        <c:forEach var="item" items="${allProduct}">
            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
            </tr>
        </c:forEach>
    </table>
</body>

</html>
<script>
    $(document).ready(function () {
        $('#example').DataTable();
    });
</script>