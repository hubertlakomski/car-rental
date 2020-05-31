<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Rent car</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.8.2/css/bulma.min.css">
    <script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
</head>
<body>
<jsp:include page="../header.jsp" />
<div>
    <form:form method="post" modelAttribute="data">
        <p>
            Nr rejestracyjny samochodu:
            <form:select path="rentedCar"
                         items="${data.availableCarsInDepartment}"
                         itemValue="id"
                         itemLabel="carDescription"
                         multiple="false" />
            <form:errors path="rentedCar"/>
        </p>
        <p>
            <form:label path="rentDate">Faktyczny czas wypożyczenia:
                <form:input path="rentDate" type="datetime-local" required="required"/>
            </form:label><form:errors path="rentDate"/>
        </p>
        <p>
            Komentarz:<br/>
            <form:textarea path="comment"/><br/>
            <form:errors path="comment" cssClass="error"/>
        </p>

        <form:input  type="hidden"  path="reservationId" value="${data.reservationId}"/>

        <p>
        <div class="control">
            <form:button class="button is-primary" type="submit">Wypożycz</form:button>
        </div>
        </p>
    </form:form>
</div>
<jsp:include page="../footer.jsp" />
</body>
</html>