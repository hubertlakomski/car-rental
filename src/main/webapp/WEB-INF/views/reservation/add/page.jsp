<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Nowa rezerwacja</title>
</head>
<body>
<jsp:include page="../../header.jsp" />

<div class="container is-widescreen">
    <div class="notification">
        <form:form method="post" modelAttribute="reservationAddData">
            <p>Przed rozpoczęciem rezerwacji zapytaj o nr klienta, jeżeli go nie ma utwórz go.</p>

            <div class="field">
                <label class="label">Klient</label>
                <div class="control">
                    <div class="select is-fullwidth">
                        <form:select itemValue="id"
                                     itemLabel="description"
                                     path="clientId"
                                     items="${clients}"
                                     multiple="false" />
                        <form:errors path="clientId"/>
                    </div>
                </div>
            </div>
            <div class="field">
                <label class="label">SIPP kod</label>
                <div class="control">
                    <div class="select is-fullwidth">
                        <form:select itemValue="id"
                                     itemLabel="code"
                                     path="sippCodeId"
                                     items="${sippCodes}"
                                     multiple="false" />
                        <form:errors path="sippCodeId"/>
                    </div>
                </div>
            </div>

            <div class="field">
                <form:label path="plannedRentDate" class="label">Planowany czas wypożyczenia</form:label>
                <div class="control">
                        <form:input path="plannedRentDate" class="input is-fullwidth" type="datetime-local" required="required"/>
                        <form:errors path="plannedRentDate"/>
                </div>
            </div>
            <div class="field">
                <form:label path="plannedReturnDate" class="label">Planowany czas zwrotu</form:label>
                <div class="control">
                        <form:input path="plannedReturnDate" class="input is-fullwidth" type="datetime-local" required="required"/>
                        <form:errors path="plannedReturnDate"/>
                </div>
            </div>

            <div class="field">
                <label class="label">Oddział wypożyczenia</label>
                <div class="control">
                    <div class="select is-fullwidth">
                        <form:select itemValue="id"
                                     itemLabel="description"
                                     path="rentDepartmentId"
                                     items="${departments}"
                                     multiple="false" />
                        <form:errors path="rentDepartmentId"/>
                    </div>
                </div>
            </div>
            <div class="field">
                <label class="label">Planowany oddział zwrotu</label>
                <div class="control">
                    <div class="select is-fullwidth">
                        <form:select itemValue="id"
                                     itemLabel="description"
                                     path="plannedReturnDepartmentId"
                                     items="${departments}"
                                     multiple="false" />
                        <form:errors path="plannedReturnDepartmentId"/>
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

            <div class="field is-grouped">
                <div class="control">
                    <form:button class="button is-link" type="submit">Utwórz rezerwację</form:button>
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