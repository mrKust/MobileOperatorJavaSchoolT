<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<title>Contracts list</title>
<body>
<h2>All Contracts</h2>
<br>

<table>
    <tr>
        <th>Contract's id</th>

        <c:forEach var="contracts" items="${allContracts}">

        <c:url var="updateButton" value="/updateContract">
            <c:param name="contractId" value="${contracts.id}"/>
        </c:url>

        <c:url var="deleteButton" value="/deleteContract">
            <c:param name="contractId" value="${contracts.id}"/>
        </c:url>

    <tr>
        <td>${contracts.id}</td>
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
       onclick="window.location.href = 'addNewContract'"/>
</body>
</html>