<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Logowanie</title>
</head>
<body>
<jsp:include page="../header.jsp" />

<div class="container is-widescreen">
    <div class="notification">
        <form method="post" action="/login">
            <div class="field">
                <label class="label">Nazwa użytkownika: </label>
                <div class="control">
                    <label>
                        <input name="username" class="input is-fullwidth" type="text" />
                    </label>
                </div>
            </div>
            <div class="field">
                <label class="label">Hasło: </label>
                <div class="control">
                    <label>
                        <input name="password" class="input is-fullwidth" type="password"/>
                    </label>
                </div>
            </div>
            <div class="field is-grouped">
                <div class="control">
                    <button class="button is-link" name="submit" onclick="validate()" type="submit">Zaloguj</button>
                </div>
                <div class="control">
                    <button type="reset" class="button is-link is-light">Wyczysć</button>
                </div>
            </div>
            <!--/*@thymesVar id="errorMessage" type="java.lang.String"*/-->
            <sec:csrfInput/>
        </form>
        <c:if test="${param.error !=null}">
            <article class="message is-danger">
                <div class="message-body">
                    <spring:message code="message.badCredentials"/>
                </div>
            </article>
        </c:if>
    </div>
</div>


<jsp:include page="../footer.jsp" />
</body>
</html>
