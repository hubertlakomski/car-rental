<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Strona główna</title>
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="container is-widescreen">
        <div class="notification">

            <h1 class="title">Oddziały</h1>
            <table class="table">
                <thead>
                <tr>
                    <th>Kod</th>
                    <th>Bieżące rezerwacje</th>
                    <th>Bieżące zwroty</th>
                    <th>Pojazdy</th>
                    <th>Pracownicy</th>
                    <th>Szczegóły</th>
                </tr>
                </thead>
                <tfoot>
                <c:forEach items="${homePageData}" var="data">
                    <tr>
                        <td>${data.departmentCode}</td>
                        <td><a href="/department/reservations?id=${data.departmentId}">zobacz</a></td>
                        <td><a href="/department/returns?id=${data.departmentId}">zobacz</a></td>
                        <td><a href="/cars/department?id=${data.departmentId}">zobacz</a></td>
                        <td><a href="/employees/department?id=${data.departmentId}">zobacz</a></td>
                        <td><a href="/details/department?id=${data.departmentId}">zobacz</a></td>
                    </tr>
                </c:forEach>
                </tfoot>
            </table>

        </div>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>