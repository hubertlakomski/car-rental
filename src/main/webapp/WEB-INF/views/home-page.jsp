<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Strona główna</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.8.2/css/bulma.min.css">
    <script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
</head>
<body>
    <jsp:include page="header.jsp" />
<%--    <section>--%>
<%--        <sec:authorize access="isAuthenticated()">Zalogowany ${username}</sec:authorize>--%>
<%--    </section>--%>
    <section>

        <a href="/department/add" class="button is-primary">Dodaj oddział</a>
        <div class="container">
            <h1 class="title">Oddziały</h1>
            <table class="table">
                <thead>
                    <tr>
                        <th>Kod</th>
                        <th>Rezerwacje</th>
                        <th>Planowane zwroty</th>
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
    </section>
    <jsp:include page="footer.jsp" />
</body>
</html>