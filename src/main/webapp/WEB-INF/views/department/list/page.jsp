<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Oddziały</title>
</head>
<body>
    <jsp:include page="../../header.jsp" />

    <div class="container">
        <div class="table-container">
            <h1 class="title">Oddziały</h1>
            <table class="table table-stripped table-bordered">
                <tr>
                    <th>kod</th>
                    <th>kraj</th>
                    <th>miasto</th>
                    <th>adres</th>
                    <th>kod pocztowy</th>
                    <th><a class="button is-success" href="?add">Dodaj</a></th>
                </tr>
                <c:choose>
                    <c:when test="${param.add!=null and param.id==null}">
                        <form method="post">
                            <tr>
                                <td><input class="input" type="text" required="required" name="code"></td>
                                <td><input class="input" type="text" required="required" name="country"></td>
                                <td><input class="input" type="text" required="required" name="city"></td>
                                <td><input class="input" type="text" required="required" name="addressLine"></td>
                                <td><input class="input" type="text" required="required" name="zipCode"></td>
                                <td>
                                    <button class="button is-success" type="submit">Zapisz</button>
                                    <a class="button is-danger" href="?">Anuluj</a>
                                </td>
                                <sec:csrfInput/>
                            </tr>
                        </form>
                    </c:when>
                </c:choose>
                <c:forEach items="${departmentDataList}" var="department">
                    <c:choose>
                        <c:when test="${param.edit!=null and param.id eq department.id.toString()}">

                            <form method="post">
                                <tr>
                                    <td><input class="input" type="text" required="required" name="code" value="${department.code}"></td>
                                    <td><input class="input" type="text" required="required" name="country" value="${department.country}"></td>
                                    <td><input class="input" type="text" required="required" name="city" value="${department.city}"></td>
                                    <td><input class="input" type="text" required="required" name="addressLine" value="${department.addressLine}"></td>
                                    <td><input class="input" type="text" required="required" name="zipCode" value="${department.zipCode}"></td>
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
                                <td>${department.code}</td>
                                <td>${department.country}</td>
                                <td>${department.city}</td>
                                <td>${department.addressLine}</td>
                                <td>${department.zipCode}</td>
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