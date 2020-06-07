<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Rezerwacje/wypożycz/${reservationRentData.reservationId}</title>
</head>
<body>
<jsp:include page="../../header.jsp" />
<div>
    <form:form method="post" modelAttribute="reservationRentData">
        <p>
            Nr rejestracyjny samochodu:
            <form:select itemValue="id"
                         itemLabel="carDescription"
                         path="rentedCarId" items="${availableCarsInDepartment}"
                         multiple="false" />
            <form:errors path="rentedCarId"/>
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

        <form:input  type="hidden"  path="reservationId" value="${reservationRentData.reservationId}"/>

        <p>
        <div class="control">
            <form:button class="button is-link" type="submit">Zatwierdź</form:button>
        </div>
        </p>
    </form:form>
</div>
<jsp:include page="../../footer.jsp" />
</body>
</html>