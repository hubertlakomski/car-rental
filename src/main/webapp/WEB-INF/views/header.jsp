<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="/webjars/bulma/0.8.2/css/bulma.min.css">
<link rel="stylesheet" href="webjars/bulma-calendar/6.0.7/dist/css/bulma-calendar.min.css">
<script src=""

<nav class="navbar"  role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
        <a class="navbar-item" style="background-color: #090" href="/">
            <img src="https://www.europcar.com.pl/resources/media_v4/NEW-Logo-size%20fit%20for%20header.png" width="112" height="28">
        </a>

        <a role="button" class="navbar-burger burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
        </a>
    </div>

    <div id="navbarBasicExample" class="navbar-menu">
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
                    <a class="navbar-item" href="/department/details">
                        Department details
                    </a>
                </div>
            </div>
        </div>
    </div>
</nav>
