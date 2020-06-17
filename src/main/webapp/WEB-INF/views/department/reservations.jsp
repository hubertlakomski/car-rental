<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Rezerwacje/${departmentCode}/bieżące</title>
</head>
<body>
<jsp:include page="../header.jsp" />

    <div class="container is-widescreen">
        <div class="notification">
        <h1 class="title">Bieżące rezerwacje oddziału - ${departmentCode} </h1>
        <table class="table">
            <thead>
            <tr>
                <th>Numer rezerwacji</th>
                <th>SIPP</th>
                <th>Planowana data wypożyczenia</th>
                <th>Planowana data zwrotu</th>
                <th>Plaowany oddział zwrotu</th>
                <th>Klient</th>
                <th>Planowana cena wynajmu</th>
                <th>Depozyt</th>
                <th></th>
            </tr>
            </thead>
            <tfoot>
            <c:forEach items="${data}" var="data">
                <tr>
                    <td>${data.reservationId}</td>
                    <td>${data.sippCode}</td>
                    <td>${data.plannedRentDate}</td>
                    <td>${data.plannedReturnDate}</td>
                    <td>${data.returnDepartmentCode}</td>
                    <td><a href="/client?id=${data.clientId}">${data.clientFullName}</a></td>
                    <td>${data.plannedCharge}</td>
                    <td>${data.deposit}</td>
                    <td><a href="/reservation?id=${data.reservationId}" class="button is-info">Szczegóły</a></td>
                </tr>
            </c:forEach>
            </tfoot>
        </table>
        <a href="/reservation/add" class="button is-primary">Dodaj rezerwację</a>
        </div>
    </div>
<jsp:include page="../footer.jsp" />
</body>
</html>