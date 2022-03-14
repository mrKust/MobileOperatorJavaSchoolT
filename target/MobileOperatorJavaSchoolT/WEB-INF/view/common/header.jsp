<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <header>
        <security:authorize access="hasRole('client')">
        <nav><a href="/">Main page</a>  <a href="/common/allOptions">Options list</a>  <a href="/common/allTariffs">Tariffs list</a>  <a href="/client/updateClient">My user info</a>  <a href="/client/updateContract">My contract</a>  <a href="/logout">Logout</a></nav>
        </security:authorize>

        <security:authorize access="hasRole('control')">
        <nav><a href="/">Main page</a>  <a href="/common/allOptions">Options list</a>  <a href="/common/allTariffs">Tariffs list</a>  <a href="/control/allClients">Clients list</a>  <a href="/control/allContracts">Contracts list</a>  <a href="/logout">Logout</a>  <a href="/control/inputNumberToSearch">Search</a></nav>
        <br><br>

        </security:authorize>

    <h2>Ecare</h2>
    </header>
</html>