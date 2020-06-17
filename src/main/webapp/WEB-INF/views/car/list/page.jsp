<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Samochody</title>
</head>
<body>
<jsp:include page="../../header.jsp" />

<div class="container">
<div class="table-container">
    <h1 class="title">Samochody</h1>
        <table class="table table-stripped table-bordered">
            <tr>
                <th>nr rejestracyjny</th>
                <th>marka</th>
                <th>model</th>
                <th>kolor</th>
                <th>rok produkcji</th>
                <th>przebieg</th>
                <th>sipp</th>
                <th>oddział</th>
                <th>status</th>
                <th><a class="button is-success" href="?add">Dodaj</a></th>
            </tr>
            <c:choose>
                <c:when test="${param.add!=null and param.id==null}">
                    <form method="post">
                        <tr>
                            <td><input class="input" type="text" name="plateNumber"></td>
                            <td><input class="input" type="text" name="brand"></td>
                            <td><input class="input" type="text" name="model"></td>
                            <td><input class="input" type="text" name="color"></td>
                            <td><input class="input" type="number" name="production"></td>
                            <td><input class="input" type="number" name="mileage"></td>
                            <td>
                                <div class="select">
                                    <select name="sippCodeId">
                                        <c:forEach items="${sippCodes}" var="code">
                                            <option value="${code.id}">${code.code}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </td>
                            <td>
                                <div class="select">
                                    <select name="departmentId" >
                                        <c:forEach items="${departments}" var="department">
                                            <option value="${department.id}">${department.code}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </td>
                            <td>
                                <div class="select">
                                    <select name="statusId" >
                                        <c:forEach items="${statuses}" var="status">
                                            <option value="${status.id}">${status.plDescription}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </td>
                            <td>
                                <button class="button is-success" type="submit">Zapisz</button>
                                <a class="button is-danger" href="?">Anuluj</a>
                            </td>
                            <sec:csrfInput/>
                        </tr>
                    </form>
                </c:when>
            </c:choose>
            <c:forEach items="${dataList}" var="data">
                <c:choose>
                    <c:when test="${param.edit!=null and param.id eq data.id.toString()}">

                        <form method="post">
                            <tr>
                                <td><input class="input" type="text" name="plateNumber" value="${data.plateNumber}"></td>
                                <td><input class="input" type="text" name="brand" value="${data.brand}"></td>
                                <td><input class="input" type="text" name="model" value="${data.model}"></td>
                                <td><input class="input" type="text" name="color" value="${data.color}"></td>
                                <td><input class="input" min="1900" max="2020" type="number" name="production" value="${data.production}"></td>
                                <td><input class="input" type="number" name="mileage" value="${data.mileage}"></td>
                                <td>
                                    <div class="select">
                                        <select name="sippCodeId">
                                            <c:forEach items="${sippCodes}" var="code">
                                                <option  ${code.id == data.sippCodeId? 'selected="selected"' : ''}
                                                        value="${code.id}">${code.code}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </td>
                                <td>
                                    <div class="select">
                                        <select name="departmentId" >
                                            <c:forEach items="${departments}" var="department">
                                                <option
                                                        ${department.id == data.departmentId ? 'selected="selected"' : ''}
                                                        value="${department.id}">

                                                        ${department.code}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </td>
                                <td>
                                    <div class="select">
                                        <select name="statusId">
                                            <c:forEach items="${statuses}" var="status">
                                                <option ${status.id == data.statusId ? 'selected="selected"' : ''}
                                                        value="${status.id}">${status.plDescription}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </td>
                                <td>

                                    <input type="hidden" name="id" value="${data.id}"/>
                                    <button class="button is-success" type="submit">Zapisz</button>
                                    <a class="button is-danger" href="?">Anuluj</a>
                                </td>
                                <sec:csrfInput/>
                            </tr>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td>${data.plateNumber}</td>
                            <td>${data.brand}</td>
                            <td>${data.model}</td>
                            <td>${data.color}</td>
                            <td>${data.production}</td>
                            <td>${data.mileage}</td>
                            <td>${data.sippCodeDsc}</td>
                            <td>${data.departmentDsc}</td>
                            <td>${data.statusDsc}</td>
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