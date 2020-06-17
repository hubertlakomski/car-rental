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
<div class="container is-widescreen">
    <div class="notification">
    <form:form method="post" modelAttribute="reservationRentData">
        <div class="field">
            <label class="label">Wybierz samochód: </label>
            <div class="control">
                <div class="select is-fullwidth">
                    <form:select itemValue="id"
                                 itemLabel="carDescription"
                                 path="rentedCarId" items="${availableCarsInDepartment}"
                                 multiple="false" />
                    <form:errors path="rentedCarId"/>
                </div>
            </div>
        </div>
        <div class="field">
            <label class="label">Komentarz</label>
            <div class="control">
                <form:textarea class="textarea is-fullwidth" path="comment"/>
                <form:errors path="comment" cssClass="error"/>
            </div>
        </div>

        <form:input  type="hidden"  path="reservationId" value="${reservationRentData.reservationId}"/>

        <div class="field is-grouped">
            <div class="control">
                <form:button class="button is-link" type="submit">Wypożycz</form:button>
            </div>
            <div class="control">
                <form:button type="reset" class="button is-link is-light">Wyczysć</form:button>
            </div>
        </div>
    </form:form>
</div>
</div>
<jsp:include page="../../footer.jsp" />
</body>
</html>