<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Zwroty/${departmentCode}/bieżące</title>
</head>
<body>
<jsp:include page="../header.jsp" />
    <div class="container is-widescreen">
        <div class="notification">
        <h1 class="title">Bieżące zwroty oddziału - ${departmentCode}</h1>
        <table class="table">
            <thead>
            <tr>
                <th>Numer rezerwacji</th>
                <th>Data wypożyczenia</th>
                <th>Planowana data zwrotu</th>
                <th>Info z oddziału wydania</th>
                <th>SIPP</th>
                <th>Samochód</th>
                <th>Klient</th>
                <th></th>
            </tr>
            </thead>
            <tfoot>
            <c:forEach items="${data}" var="department">
                <tr>
                    <td>${department.reservationId}</td>
                    <td>${department.realRentDate}</td>
                    <td>${department.plannedReturnDate}</td>
                    <td>${department.rentComment}</td>
                    <td>${department.sippCode}</td>
                    <td><a href="/car?id=${department.carId}">${department.carDescription}</a></td>
                    <td><a href="/client?id=${department.clientId}">${department.clientFullName}</a></td>
                    <td><a href="/reservation?id=${department.reservationId}" class="button is-info">Szczegóły</a></td>
                </tr>
            </c:forEach>
            </tfoot>
        </table>
        </div>
    </div>
<jsp:include page="../footer.jsp" />
</body>
</html>