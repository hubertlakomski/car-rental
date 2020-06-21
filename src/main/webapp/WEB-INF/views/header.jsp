<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bulma/0.8.2/css/bulma.min.css">
<link rel="stylesheet" href="webjars/font-awesome/5.13.0/css/all.css">

<style>
    #navbar-item {
        background: url(https://www.autoeurope.eu/EUR/assets/Image/ico_driver.png) no-repeat center center;
        background-size: cover;
        width: 80px;
        height: 80px;
    }
</style>

<nav class="navbar is-transparent">
    <div class="navbar-brand">
        <a id="navbar-item" href="/"></a>
    </div>
    <sec:authorize access="isAuthenticated()">
    <div class="navbar-menu">
        <div class="navbar-start">

            <a class="navbar-item" href="/reservations">
                Rezerwacje
            </a>
            <a class="navbar-item" href="/departments">
                Oddziały
            </a>
            <a class="navbar-item" href="/clients">
                Klienci
            </a>
            <a class="navbar-item" href="/cars">
                Samochody
            </a>
            <a class="navbar-item" href="/employees">
                Pracownicy
            </a>
            </div>
        </div>
        <div class="navbar-end">
            <div class="navbar-item">
                <div class="buttons">
                    <a href="/logout" class="button is-light">
                        Wyloguj
                    </a>
                </div>
            </div>
        </div>
    </div>
    </sec:authorize>
</nav>
