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
        <div class="table-container">
        <h1 class="title">Bieżące rezerwacje oddziału - ${departmentCode} </h1>
        <table class="table table-stripped table-bordered">
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
            <c:forEach items="${data}" var="department">
                <tr>
                    <td>${department.reservationId}</td>
                    <td>${department.sippCode}</td>
                    <td>${department.plannedRentDate}</td>
                    <td>${department.plannedReturnDate}</td>
                    <td>${department.returnDepartmentCode}</td>
                    <td><a href="/client?id=${department.clientId}">${department.clientFullName}</a></td>
                    <td>${department.plannedCharge}</td>
                    <td>${department.deposit}</td>
                    <td><a href="/reservation?id=${department.reservationId}" class="button is-info">Szczegóły</a></td>
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