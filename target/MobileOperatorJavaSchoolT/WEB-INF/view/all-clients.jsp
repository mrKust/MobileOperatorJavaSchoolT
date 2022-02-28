<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<title>Clients list</title>
<body>
<h2>All Clients</h2>
<br>

<table>
    <tr>
        <th>Client's name</th>
        <th>Client's surname</th>
        <th>Mobile number</th>
        <th>email</th>
        <th>Block status</th>
        <th>Operations</th>

        <c:forEach var="clients" items="${allClients}">

        <c:url var="updateButton" value="/updateClient">
            <c:param name="clientId" value="${clients.id}"/>
        </c:url>

        <c:url var="deleteButton" value="/deleteClient">
            <c:param name="clientId" value="${clients.id}"/>
        </c:url>

    <tr>
        <td>${clients.first_name}</td>
        <td>${clients.surname}</td>
        <td>${clients.phone_number}</td>
        <td>${clients.email_address}</td>
        <td>${clients.clientNumberBlockStatus}</td>
        <td>
            <input type="button" value="Update"
                   onclick="window.location.href = '${updateButton}'"/>
            <input type="button" value="Delete"
                   onclick="window.location.href = '${deleteButton}'"/>
        </td>
    </tr>

    </c:forEach>
    </tr>
</table>

<br>

<input type="button" value="Add"
       onclick="window.location.href = 'addNewClient'"/>
</body>
</html>