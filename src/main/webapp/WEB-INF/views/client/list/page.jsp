<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Klienci</title>
</head>
<body>
<jsp:include page="../../header.jsp" />


<div class="container">
<div class="table-container">
    <h1 class="title">Klienci</h1>
        <table class="table">
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
            <c:forEach items="${dataList}" var="data">
                <c:choose>
                    <c:when test="${param.edit!=null and param.id eq data.id.toString()}">

                        <form method="post">
                            <tr>

                                <td>${data.id}</td>
                                <td><input type="text" name="firstName" value="${data.firstName}"></td>
                                <td><input type="text" name="lastName" value="${data.lastName}"></td>
                                <td><input type="text" name="numberOfDrivingLicence" value="${data.numberOfDrivingLicence}"></td>
                                <td><input type="text" name="numberOfId" value="${data.numberOfId}"></td>
                                <td><input type="email" name="email" value="${data.email}"></td>
                                <td><input type="text" name="country" value="${data.country}"></td>
                                <td><input type="text" name="city" value="${data.city}"></td>
                                <td><input type="text" name="zippCode" value="${data.zippCode}"></td>
                                <td><input type="text" name="addressLine" value="${data.addressLine}"></td>

                                <td>

                                    <input type="hidden" name="id" value="${data.id}"/>
                                    <button class="button is-succes" type="submit">Zapisz</button>
                                    <a class="button is-link" href="?">Anuluj</a>
                                </td>
                                <sec:csrfInput/>
                            </tr>
                        </form>
                    </c:when>
                    <c:otherwise>
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
                            <td><a class="button is-link" href="?edit&id=${data.id}">Edytuj</a> </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </table>
</div>
</div>

<jsp:include page="../../footer.jsp" />
</body>
</html>