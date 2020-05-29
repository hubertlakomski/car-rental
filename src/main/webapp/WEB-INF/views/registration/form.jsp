<%--
  Created by IntelliJ IDEA.
  User: hubert
  Date: 10.05.2020
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Rejestracja konta</title>
</head>
    <body>
        <h1>Zarejestruj nowe konto</h1>
            <div>
                <%--@elvariable id="registrationData" type=""--%>
                <form:form method="post" action="/register" modelAttribute="registrationData">
                    <p>
                        <form:label path="username">Nazwa użytkownika: <form:input path="username" required="required"/>
                        </form:label><form:errors path="username"/>
                    </p>
                    <p>
                        <form:label path="email">Adres e-mail: <form:input path="email" type="email" required="required"/>
                        </form:label><form:errors path="email"/>
                    </p>
                    <p>
                        <form:label path="password">Hasło: <form:password path="password" required="required"/>
                        </form:label><form:errors path="password"/>
                    </p>
                    <p>
                        <div class="control">
                            <form:button class="button is-primary" type="submit">Zarejestruj</form:button>
                            <form:button type="reset">Wyczyść</form:button>
                        </div>
                    </p>
                </form:form>
            </div>
    </body>
</html>
