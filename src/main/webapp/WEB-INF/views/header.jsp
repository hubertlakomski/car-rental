<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<link rel="stylesheet" href="/webjars/bulma/0.8.2/css/bulma.min.css">
<link rel="stylesheet" href="webjars/bulma-calendar/6.0.7/dist/css/bulma-calendar.min.css">

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

    <div class="navbar-menu">
        <div class="navbar-start">
            <a class="navbar-item" href="/">
                Home
            </a>

            <a class="navbar-item" href="/reservations">
                Reservations
            </a>

            <a class="navbar-item" href="/returns">
                Returns
            </a>

            <div class="navbar-item has-dropdown is-hoverable">
                <a class="navbar-link">
                    More
                </a>

                <div class="navbar-dropdown">
                    <a class="navbar-item" href="/clients">
                        Clients
                    </a>
                    <a class="navbar-item" href="/cars">
                        Cars
                    </a>
                    <a class="navbar-item" href="/employees">
                        Employees
                    </a>
                </div>
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
</nav>
