<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Logowanie</title>
</head>
<body>
<jsp:include page="header.jsp" />
<div class="container is-widescreen">
    <div class="notification">
        <form method="post">
            <div class="field">
                <label class="label">Nazwa użytkownika: </label>
                <div class="control">
                    <label>
                        <input name="username" class="input is-fullwidth" type="text" required="required"/>
                    </label>
                </div>
            </div>
            <div class="field">
                <label class="label">Hasło: </label>
                <div class="control">
                    <label>
                        <input name="password" class="input is-fullwidth" type="password" required="required"/>
                    </label>
                </div>
            </div>
            <div class="field is-grouped">
                <div class="control">
                    <button class="button is-link" type="submit">Zaloguj</button>
                </div>
                <div class="control">
                    <button type="reset" class="button is-link is-light">Wyczysć</button>
                </div>
            </div>
            <sec:csrfInput/>
        </form>
    </div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
