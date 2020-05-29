<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Szczegóły klienta ${data.firstName} ${data.lastName}</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.8.2/css/bulma.min.css">
    <script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
</head>
<body>
<jsp:include page="../../header.jsp" />
<section>

    <a href="/client/add" class="button is-primary">Dodaj klienta</a>
    <div class="container">
        <h1 class="title">Klient ${data.firstName} ${data.lastName}</h1>
        <table class="table">
            <thead>
            <tr>
                <th>Imię</th>
                <th>Nazwisko</th>
                <th>Email</th>
                <th>Numer prawa jazdy</th>
                <th>Numer dowodu os.</th>
                <th>Adres</th>
            </tr>
            </thead>
            <tfoot>
                <tr>
                    <td>${data.firstName}</td>
                    <td>${data.lastName}</td>
                    <td>${data.email}</td>
                    <td>${data.numberOfDrivingLicense}</td>
                    <td>${data.numberOfId}</td>
                    <td>${data.address}</td>
                </tr>
            </tfoot>
        </table>
    </div>
</section>
<jsp:include page="../../footer.jsp" />
</body>
</html>