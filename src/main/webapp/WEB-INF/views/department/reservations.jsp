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
<jsp:include page="../header.jsp" />
<section>

    <a href="/reservations/add" class="button is-primary">Dodaj rezerwację</a>
    <div class="container">
        <h1 class="title">Rezerwacje oddziału ${departmentCode}</h1>
        <table class="table">
            <thead>
            <tr>
                <th>Numer rezerwacji</th>
                <th>SIPP</th>
                <th>Planowana data wypożyczenia</th>
                <th>Planowana data zwrotu</th>
                <th>Oddział zwrotu</th>
                <th>Klient</th>
                <th>Cena wynajmu</th>
                <th>Depozyt</th>
                <th>Akcje</th>
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
                    <td><a href="/reservations/rentProcess?id=${data.reservationId}">wypożycz</a>
                    <a href="/reservations/edit?id=${data.reservationId}">edytuj</a></td>
                </tr>
            </c:forEach>
            </tfoot>
        </table>
    </div>
</section>
<jsp:include page="../footer.jsp" />
</body>
</html>