<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Klienci</title>
</head>
<body>
<jsp:include page="../../header.jsp" />

<div class="container is-widescreen">
    <div class="notification">

        <h1 class="title">Klienci</h1>
        <table class="table">
            <thead>
            <tr>
                <th>nr klienta</th>
                <th>imię</th>
                <th>nazwisko</th>
                <th>nr prawa jazdy</th>
                <th>nr dokumentu tożsamości</th>
                <th>email</th>
                <th>kraj</th>
                <th>miasto</th>
                <th>kod pocztowy</th>
                <th>adres</th>
            </tr>
            </thead>
            <tfoot>
            <c:forEach items="${dataList}" var="data">
                <tr>
                    <td>${data.id}</td>
                    <td>${data.firstName}</td>
                    <td>${data.lastName}</td>
                    <td>${data.numberOfDrivingLicence}</td>
                    <td>${data.numberOfId}</td>
                    <td>${data.email}</td>
                    <td>${data.country}</td>
                    <td>${data.city}</td>
                    <td>${data.zippCode}</td>
                    <td>${data.addressLine}</td>
                    <td><a class="button is-primary" href="/clients?edit&id=${data.id}">Edytuj</a> </td>
                </tr>
            </c:forEach>
            </tfoot>
        </table>

    </div>
</div>

<jsp:include page="../../footer.jsp" />
</body>
</html>