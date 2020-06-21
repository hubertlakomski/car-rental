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

<%--source: https://bulma.io/documentation/components/navbar/--%>
<script>
    document.addEventListener('DOMContentLoaded', () => {

        // Get all "navbar-burger" elements
        const $navbarBurgers = Array.prototype.slice.call(document.querySelectorAll('.navbar-burger'), 0);

        // Check if there are any navbar burgers
        if ($navbarBurgers.length > 0) {

            // Add a click event on each of them
            $navbarBurgers.forEach( el => {
                el.addEventListener('click', () => {

                    // Get the target from the "data-target" attribute
                    const target = el.dataset.target;
                    const $target = document.getElementById(target);

                    // Toggle the "is-active" class on both the "navbar-burger" and the "navbar-menu"
                    el.classList.toggle('is-active');
                    $target.classList.toggle('is-active');

                });
            });
        }

    });
</script>

<nav class="navbar" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
        <a id="navbar-item" class="navbar-item" href="/"></a>
        <sec:authorize access="isAuthenticated()">
        <a role="button" class="navbar-burger burger" aria-label="menu" aria-expanded="false" data-target="main-navbar">
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
        </a>
        </sec:authorize>
    </div>
    <sec:authorize access="isAuthenticated()">

    <div id="main-navbar" class="navbar-menu">
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
