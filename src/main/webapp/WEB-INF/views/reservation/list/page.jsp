<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Wszystkie rezerwacje</title>
    <script>
        function search_table(){
            // Declare variables
            var input, filter, table, tr, td, i;
            input = document.getElementById("search_field");
            filter = input.value.toUpperCase();
            table = document.getElementById("reservations");
            tr = table.getElementsByTagName("tr");

            // Loop through all table rows, and hide those who don't match the search query
            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td") ;
                for(j=0 ; j<td.length ; j++)
                {
                    let tdata = td[j] ;
                    if (tdata) {
                        if (tdata.innerHTML.toUpperCase().indexOf(filter) > -1) {
                            tr[i].style.display = "";
                            break ;
                        } else {
                            tr[i].style.display = "none";
                        }
                    }
                }
            }
        }
    </script>
</head>
<body>
<jsp:include page="../../header.jsp" />
<div class="container">
    <div class="table-container">
        <nav class="level">
            <div class="level-left">
                <div class="level-item">
                    <p class="title">Rezerwacje</p>
                </div>
            </div>
            <div class="level-right">
                <div class="field has-addons">
                    <p class="control">
                        <input id="search_field" onkeyup="search_table()" class="input" type="text" placeholder="znajdź rezerwację...">
                    </p>
                </div>
            </div>
        </nav>
        <table id="reservations" class="table is-hoverable is-fullwidth">

            <tr>
                <th><abbr title="numer rezerwacji">nr</abbr></th>
                <th><abbr title="planowana data wypożyczenia">data wynajęcia</abbr></th>
                <th><abbr title="planowana data zwrotu">data zwrotu</abbr></th>
                <th><abbr title="oddział wydający">miejsce odbioru</abbr></th>
                <th><abbr title="oddział zwrotu">miejsce zwrotu</abbr></th>
                <th>klient</th>
                <th><abbr title="kod SIPP">sipp</abbr></th>
                <th><abbr title="całkowita cena za cały wynajem">cena</abbr></th>
                <th><abbr title="wyliczony depozyt">depozyt</abbr></th>
                <th><abbr title="ważne uwagi, wskazówki, prośby">komentarz</abbr></th>
                <th><a class="button is-success" href="?add">Dodaj</a></th>
            </tr>
            <c:choose>
                <c:when test="${param.add!=null and param.id==null}">
                    <form method="post">
                        <tr>
                            <td></td>
                            <td><input class="input" type="datetime-local" required="required" name="plannedRentDate"></td>
                            <td><input class="input" type="datetime-local" required="required" name="plannedReturnDate"></td>
                            <td>
                                <div class="select is-primary">
                                    <select name="rentDepartmentId">
                                        <c:forEach items="${departments}" var="department">
                                            <option value="${department.id}">${department.code}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </td>
                            <td>
                                <div class="select is-primary">
                                    <select name="plannedReturnDepartmentId">
                                        <c:forEach items="${departments}" var="department">
                                            <option value="${department.id}">${department.code}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </td>
                            <td>
                                <div class="select is-primary">
                                    <select name="clientId">
                                        <c:forEach items="${clients}" var="client">
                                            <option value="${client.id}">${client.description}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </td>
                            <td>
                                <div class="select is-primary">
                                    <select name="sippCodeId">
                                        <c:forEach items="${sippcodes}" var="sippCode">
                                            <option value="${sippCode.id}">${sippCode.code}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </td>
                            <td></td>
                            <td></td>
                            <td><input class="textarea" type="text" name="comment"></td>
                            <td>
                                <button class="button is-success" type="submit">Zapisz</button>
                                <a class="button is-danger" href="?">Anuluj</a>
                            </td>
                            <sec:csrfInput/>
                        </tr>
                    </form>
                </c:when>
            </c:choose>
            <c:forEach items="${reservations}" var="reservation">
            <c:choose>
                    <c:when test="${param.edit!=null and param.id eq reservation.id.toString()}">

                        <form method="post">
                            <tr>
                                <td>${reservation.id}</td>
                                <td>
                                    <input class="input" type="datetime-local" required="required"
                                           name="plannedRentDate" value="${reservation.plannedRentDateLDT}">
                                </td>
                                <td>
                                    <input class="input" type="datetime-local" required="required"
                                           name="plannedReturnDate" value="${reservation.plannedReturnDateLDT}">
                                </td>
                                <td>
                                    <div class="select">
                                        <select required="required" name="rentDepartmentId">
                                            <c:forEach items="${departments}" var="department">
                                                <option ${department.id == reservation.rentDepartmentId? "selected='selected'" : ''}
                                                        value="${department.id}">${department.code}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </td>
                                <td>
                                    <div class="select">
                                        <select required="required" name="plannedReturnDepartmentId">
                                            <c:forEach items="${departments}" var="department">
                                                <option ${department.id == reservation.returnDepartmentId? "selected='selected'" : ''}
                                                        value="${department.id}">${department.code}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </td>
                                <td>
                                    <div class="select">
                                        <select required="required" name="clientId">
                                            <c:forEach items="${clients}" var="client">
                                                <option ${client.id == reservation.clientId? "selected='selected'" : ''}
                                                        value="${client.id}">${client.description}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </td>
                                <td>
                                    <div class="select">
                                        <select required="required" name="sippCodeId">
                                            <c:forEach items="${sippcodes}" var="sippCode">
                                                <option ${sippCode.id == reservation.sippCodeId? "selected='selected'" : ''}
                                                        value="${sippCode.id}">${sippCode.code}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </td>
                                <td >
                                    <input class="input" style="width: 100px" type="number" required="required"
                                           name="plannedRentalFee" value="${reservation.plannedRentalFee}">
                                </td>
                                <td>
                                    <sec:authorize var="isManager" access="hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')"/>
                                    <c:choose>
                                        <c:when test="${isManager}">
                                            <input class="input" style="width: 100px" type="number" required="required"
                                                   name="deposit" value="${reservation.deposit}">
                                        </c:when>
                                        <c:otherwise>
                                            ${reservation.deposit}
                                        </c:otherwise>
                                    </c:choose>
                                </td>

                                <td>
                                    <input class="textarea" type="text" style="width: 150px"
                                           name="comment" value="${reservation.comment}">
                                </td>
                                <td>
                                    <input type="hidden" name="id" value="${reservation.id}"/>
                                    <button class="button is-success" type="submit">Zapisz</button>
                                    <a class="button is-danger" href="?">Anuluj</a>
                                </td>
                                <sec:csrfInput/>
                            </tr>
                        </form>
                    </c:when>
                    <c:otherwise>

                        <tr>
                            <td><a title="szczegóły" href="/reservation?id=${reservation.id}" class="button is-info">${reservation.id}</a> </td>
                            <td class="is-center">${reservation.plannedRentDate}</td>
                            <td>${reservation.plannedReturnDate}</td>
                            <td>${reservation.rentDepartmentCode}</td>
                            <td>${reservation.returnDepartmentCode}</td>
                            <td>${reservation.clientFullName}</td>
                            <td>${reservation.sippCode}</td>
                            <td>${reservation.plannedRentalFee}</td>
                            <td>${reservation.deposit}</td>
                            <td>${reservation.comment}</td>
                            <td><a class="button is-link" href="?edit&id=${reservation.id}">Edytuj</a> </td>

                        </tr>

                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </table>
    </div>
</div>

<jsp:include page="../../footer.jsp" />
</body>
</html>