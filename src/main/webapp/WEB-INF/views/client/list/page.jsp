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
        <table class="table table-stripped table-bordered">
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
                <th><a class="button is-success" href="?add">Dodaj</a></th>
            </tr>
            <c:choose>
                <c:when test="${param.add!=null and param.id==null}">
                    <form method="post">
                        <tr>
                            <td></td>
                            <td><input class="input" type="text" required="required" name="firstName"></td>
                            <td><input class="input" type="text" required="required" name="lastName"></td>
                            <td><input class="input" type="text" required="required" name="numberOfDrivingLicence"></td>
                            <td><input class="input" type="text" required="required" name="numberOfId"></td>
                            <td><input class="input" type="email" name="email"></td>
                            <td><input class="input" type="text" required="required" name="country"></td>
                            <td><input class="input" type="text" required="required" name="city"></td>
                            <td><input class="input" type="text" name="zippCode"></td>
                            <td><input class="input" type="text" required="required" name="addressLine"></td>

                            <td>
                                <button class="button is-success" type="submit">Zapisz</button>
                                <a class="button is-danger" href="?">Anuluj</a>
                            </td>
                            <sec:csrfInput/>
                        </tr>
                    </form>
                </c:when>
            </c:choose>
            <c:forEach items="${dataList}" var="department">
                <c:choose>
                    <c:when test="${param.edit!=null and param.id eq department.id.toString()}">

                        <form method="post">
                            <tr>
                                <td>${department.id}</td>
                                <td><input class="input" type="text" required="required" name="firstName" value="${department.firstName}"></td>
                                <td><input class="input" type="text" required="required" name="lastName" value="${department.lastName}"></td>
                                <td><input class="input" type="text" required="required" name="numberOfDrivingLicence" value="${department.numberOfDrivingLicence}"></td>
                                <td><input class="input" type="text" required="required" name="numberOfId" value="${department.numberOfId}"></td>
                                <td><input class="input" type="email" name="email" value="${department.email}"></td>
                                <td><input class="input" type="text" required="required" name="country" value="${department.country}"></td>
                                <td><input class="input" type="text" required="required" name="city" value="${department.city}"></td>
                                <td><input class="input" type="text" name="zippCode" value="${department.zippCode}"></td>
                                <td><input class="input" type="text" required="required" name="addressLine" value="${department.addressLine}"></td>

                                <td>

                                    <input type="hidden" name="id" value="${department.id}"/>
                                    <button class="button is-success" type="submit">Zapisz</button>
                                    <a class="button is-danger" href="?">Anuluj</a>
                                </td>
                                <sec:csrfInput/>
                            </tr>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td>${department.id}</td>
                            <td>${department.firstName}</td>
                            <td>${department.lastName}</td>
                            <td>${department.numberOfDrivingLicence}</td>
                            <td>${department.numberOfId}</td>
                            <td>${department.email}</td>
                            <td>${department.country}</td>
                            <td>${department.city}</td>
                            <td>${department.zippCode}</td>
                            <td>${department.addressLine}</td>
                            <td><a class="button is-link" href="?edit&id=${department.id}">Edytuj</a> </td>
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