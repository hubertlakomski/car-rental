<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Pracownicy</title>
</head>
<body>
    <jsp:include page="../../header.jsp" />

    <div class="container">
        <div class="table-container">
            <h1 class="title">Pracownicy</h1>
            <table class="table table-stripped table-bordered">
                <tr>
                    <th>id</th>
                    <th>imię</th>
                    <th>nazwisko</th>
                    <th>oddział</th>
                    <th>login</th>
                    <th>hasło</th>
                    <th>uprawnienia</th>
                    <th>aktywny?</th>
                    <th><a class="button is-success" href="?add">Dodaj</a></th>
                </tr>
                <c:choose>
                    <c:when test="${param.add!=null and param.id==null}">
                        <form method="post">
                            <tr>
                                <td></td>
                                <td><input class="input" type="text" required="required" name="firstName"></td>
                                <td><input class="input" type="text" required="required" name="lastName"></td>
                                <td>
                                    <div class="select">
                                        <select name="departmentId">
                                            <c:forEach items="${departments}" var="department">
                                                <option value="${department.id}">${department.code}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </td>
                                <td><input class="input" type="text" required="required" name="username"></td>
                                <td><input class="input" type="password" required="required" name="password"></td>
                                <td>
                                    <div class="select is-multiple">
                                        <select name="roleIdList" multiple="true">
                                            <c:forEach items="${roles}" var="role">
                                                <option value="${role.id}">${role.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </td>
                                <td>
                                    <input type="checkbox" name="active" value="1" checked>
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
                <c:forEach items="${employeeDataList}" var="employee">
                    <c:choose>
                        <c:when test="${param.edit!=null and param.id eq employee.id.toString()}">

                            <form method="post">
                                <tr>
                                    <td>${employee.id}</td>
                                    <td><input class="input" type="text" required="required" name="firstName" value="${employee.firstName}"></td>
                                    <td><input class="input" type="text" required="required" name="lastName" value="${employee.lastName}"></td>
                                    <td>
                                        <div class="select">
                                            <select name="departmentId">
                                                <option></option>
                                                <c:forEach items="${departments}" var="department">
                                                    <option ${department.id == employee.departmentId? 'selected="selected"' : ''}
                                                            value="${department.id}">${department.code}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                    <td>${employee.username}</td>
                                    <td><input class="input" type="password" placeholder="wprowadź nowe lub pozostaw puste.." name="password"></td>
                                    <td>
                                        <div class="select is-multiple">
                                            <select name="roleIdList" multiple="true">
                                                <c:forEach items="${roles}" var="role">

                                                        <c:if test="${employee.roles.contains(role.name)}">
                                                            <option selected="selected" value="${role.id}">${role.name}</option>
                                                        </c:if>


                                                        <c:if test="${!employee.roles.contains(role.name)}">
                                                            <option value="${role.id}">${role.name}</option>
                                                        </c:if>

                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                    <td>
                                        <input type="checkbox" name="active" value="1"
                                               <c:if test="${employee.active == true}">checked="checked"</c:if>
                                        />
                                    </td>

                                    <td>

                                        <input type="hidden" name="id" value="${employee.id}"/>
                                        <button class="button is-success" type="submit">Zapisz</button>
                                        <a class="button is-danger" href="?">Anuluj</a>
                                    </td>
                                    <sec:csrfInput/>
                                </tr>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td>${employee.id}</td>
                                <td>${employee.firstName}</td>
                                <td>${employee.lastName}</td>
                                <td>${employee.departmentCode}</td>
                                <td>${employee.username}</td>
                                <td>*****</td>
                                <td>
                                    <c:forEach items="${employee.roles}" var="role">
                                        ${role}<br/>
                                    </c:forEach>
                                </td>
                                <td>
                                    <c:if test="${employee.active}">
                                        tak
                                    </c:if>
                                    <c:if test="${employee.active == false}">
                                        nie
                                    </c:if>
                                </td>
                                <td><a class="button is-link" href="?edit&id=${employee.id}">Edytuj</a> </td>
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