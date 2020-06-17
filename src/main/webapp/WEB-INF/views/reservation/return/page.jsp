<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
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
<div class="container is-widescreen">
    <div class="notification">
    <form:form method="post" modelAttribute="reservationReturnData">

        <div class="field">
            <div class="control">
                <b>Planowany oddział zwrotu: </b>${reservationData.plannedReturnDepartment}
            </div>
        </div>

        <div class="field">
            <label class="label">Oddział zwrotu: </label>
            <div class="control">
                <div class="select is-fullwidth">
                    <form:select itemValue="id"
                                 itemLabel="description"
                                 path="realReturnDepartmentId"
                                 items="${departments}"
                                 multiple="false" />
                    <form:errors path="realReturnDepartmentId"/>
                </div>
            </div>
        </div>

        <div class="field">
            <div class="control">
                <b>Planowany czas zwrotu: </b> ${reservationData.plannedReturnDate}
            </div>
        </div>
        <div class="field">
            <div class="control">
                <b>Aktualny czas: </b>
            <%
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                LocalDateTime now = LocalDateTime.now();

                out.print(now.format(formatter));
            %>
            </div>
        </div>
        <div class="field">
            <div class="control">
                <b>Planowana opłata za wypożyczenie: </b>${reservationData.plannedRentalFee}
            </div>
        </div>
        <div class="field">
            <label class="label">Opłata za wypożyczenie:</label>
            <div class="control">
                <form:input path="realRentalFee" class="input is-fullwidth" type="number" required="required"/>
                <form:errors path="realRentalFee"/>
            </div>
        </div>
        <div class="field">
            <div class="control">
                <b>Pobrany depozyt: </b>${reservationData.deposit}
            </div>
        </div>
        <div class="field">
            <label class="label">Kwota obciążenia depozytu:</label>
            <div class="control">
                <form:input path="depositCharge" class="input is-fullwidth" type="number" required="required"/>
                <form:errors path="depositCharge"/>
            </div>
        </div>

        <div class="field">
            <div class="control">
                <b>Ostatni przebieg: </b>${reservationData.lastMileage}
            </div>
        </div>

        <div class="field">
            <label class="label">Przebieg przy zwrocie: </label>
            <div class="control">
                <form:input path="mileage" class="input is-fullwidth" type="number" required="required"/>
                <form:errors path="mileage"/>
            </div>
        </div>

        <div class="field">
            <div class="control">
                <b>Komentarz do rezerwacji: </b>${reservationData.reservationComment}
            </div>
        </div>

        <div class="field">
            <div class="control">
                <b>Komentarz z oddziału wypożyczenia: </b>${reservationData.rentComment}
            </div>
        </div>

        <div class="field">
            <label class="label">Komentarz do zwrotu:</label>
            <div class="control">
                <form:textarea class="input full-width" path="comment"/><br/>
                <form:errors path="comment" cssClass="error"/>
            </div>
        </div>

        <form:input  type="hidden"  path="reservationId" value="${reservationReturnData.reservationId}"/>
        <sec:csrfInput/>

        <div class="field is-grouped">
            <div class="control">
                <form:button class="button is-link" type="submit">Zwróć</form:button>
            </div>
            <div class="control">
                <form:button type="reset" class="button is-link is-light">Wyczysć</form:button>
            </div>
        </div>
    </form:form>

</div>
<jsp:include page="../../footer.jsp" />
</body>
</html>