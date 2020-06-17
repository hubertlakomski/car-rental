<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Rezerwacje/szczegóły/${data.reservationId}</title>
</head>
<body>
<jsp:include page="../../header.jsp" />
<div class="container">
    <div class="notification is-fullwidth">
        <h1 class="title">Szczegóły rezerwacji</h1>
        <table class="table">
            <tr>
                <th>Numer rezerwacji</th> <td>${data.reservationId}</td>
            </tr>
            <tr>
                <th>SIPP</th> <td>${data.sippCode}</td>
            </tr>
            <tr>
                <th>Samochód</th> <td>${data.rentedCar}</td>
            </tr>
            <tr>
                <th>Planowany czas wynajmu</th> <td>${data.plannedRentDate}</td>
            </tr>
            <tr>
                <th>Rzeczywisty czas wynajmu</th> <td>${data.realRentDate}</td>
            </tr>
            <tr>
                <th>Planowany czas zwrotu</th> <td>${data.plannedReturnDate}</td>
            </tr>
            <tr>
                <th>Rzeczywisty czas zwrotu</th> <td>${data.realReturnDate}</td>
            </tr>
            <tr>
                <th>Oddział wynajmu</th> <td>${data.rentDepartment}</td>
            </tr>
            <tr>
                <th>Planowany oddział zwrotu</th> <td>${data.plannedReturnDepartment}</td>
            </tr>
            <tr>
                <th>Rzeczywisty oddział zwrotu</th> <td>${data.realReturnDepartment}</td>
            </tr>
            <tr>
                <th>Klient</th> <td>${data.client}</td>
            </tr>
            <tr>
                <th>Planowana opłata</th> <td>${data.plannedRentalFee}</td>
            </tr>
            <tr>
                <th>Rzeczywista opłata</th> <td>${data.realRentalFee}</td>
            </tr>
            <tr>
                <th>Depozyt</th> <td>${data.deposit}</td>
            </tr>
            <tr>
                <th>Obciążenie depozytu</th> <td>${data.depositCharge}</td>
            </tr>
            <tr>
                <th>Komentarz do rezerwacji</th> <td>${data.reservationComment}</td>
            </tr>
            <tr>
                <th>Komentarz do wydania</th> <td>${data.rentComment}</td>
            </tr>
            <tr>
                <th>Komentarz do zwrotu</th> <td>${data.returnComment}</td>
            </tr>
        </table>

        <c:if test="${data.realRentDate==null}">
            <a href="/reservation/rent?reservationId=${data.reservationId}" class="button is-success">Wypożycz</a>
        </c:if>
        <c:if test="${data.realReturnDate==null && data.realRentDate!=null}">
            <a href="/reservation/return?reservationId=${data.reservationId}" class="button is-danger">Przyjmij zwrot</a>
        </c:if>

    </div>
</div>

<jsp:include page="../../footer.jsp" />
</body>
</html>