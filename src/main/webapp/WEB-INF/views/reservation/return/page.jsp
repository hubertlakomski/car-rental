<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Rezerwacje/zwróć/${reservationReturnData.reservationId}</title>
</head>
<body>
<jsp:include page="../../header.jsp" />
<div>
    <form:form method="post" modelAttribute="reservationReturnData">

        <p>Planowany oddział zwrotu: ${reservationData.plannedReturnDepartment}</p>
        <p>
            Oddział zwrotu:
            <form:select itemValue="id"
                         itemLabel="description"
                         path="realReturnDepartmentId"
                         items="${departments}"
                         multiple="false" />
            <form:errors path="realReturnDepartmentId"/>
        </p>

        <p>Planowany czas zwrotu: ${reservationData.plannedReturnDate}</p>
        <p>
            <form:label path="realReturnDate">Faktyczny czas zwrotu:
                <form:input path="realReturnDate" type="datetime-local" required="required"/>
            </form:label><form:errors path="realReturnDate"/>
        </p>
        <p>Planowana opłata za wypożyczenie: ${reservationData.plannedRentalFee}</p>
        <p>
            <form:label path="realRentalFee">Opłata za wypożyczenie:
                <form:input path="realRentalFee" type="text" required="required"/>
            </form:label><form:errors path="realRentalFee"/>
        </p>
        <p>Depozyt: ${reservationData.deposit}</p>
        <p>
            <form:label path="depositCharge">Kwota obciążenia depozytu:
                <form:input path="depositCharge" type="text" required="required"/>
            </form:label><form:errors path="depositCharge"/>
        </p>
        <p>Ostatni przebieg: ${reservationData.lastMileage}</p>
        <p>
            <form:label path="mileage">Aktualny przebieg:
                <form:input path="mileage" type="number" required="required"/>
            </form:label><form:errors path="mileage"/>
        </p>
        <p>Komentarz przy wypożyczeniu: ${reservationData.comment}</p>
        <p>
            Komentarz do zwrotu:<br/>
            <form:textarea path="comment"/><br/>
            <form:errors path="comment" cssClass="error"/>
        </p>

        <form:input  type="hidden"  path="reservationId" value="${reservationReturnData.reservationId}"/>

        <p>
        <div class="control">
            <form:button class="button is-primary" type="submit">Zwróć</form:button>
        </div>
        </p>
    </form:form>
</div>
<jsp:include page="../../footer.jsp" />
</body>
</html>