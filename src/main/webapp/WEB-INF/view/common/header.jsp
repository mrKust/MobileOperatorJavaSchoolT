<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
    <body>
        <security:authorize access="hasRole('client')">
        <nav><a href="/">Main page</a>  <a href="/common/allOptions">Options list</a>  <a href="/common/allTariffs">Tariffs list</a>  <a href="/client/updateClient">My user info</a>  <a href="/client/updateContract">My contract</a>  <a href="/logout">Logout</a></nav>
        </security:authorize>

        <security:authorize access="hasRole('control')">
        <nav><a href="/">Main page</a>  <a href="/common/allOptions">Options list</a>  <a href="/common/allTariffs">Tariffs list</a>  <a href="/control/allClients">Clients list</a>  <a href="/control/allContracts">Contracts list</a>  <a href="/logout">Logout</a></nav>
        <br><br>
            <jsp:include page="search.jsp"/>
        </security:authorize>

    <h2>Ecare</h2>
    </body>
</html>