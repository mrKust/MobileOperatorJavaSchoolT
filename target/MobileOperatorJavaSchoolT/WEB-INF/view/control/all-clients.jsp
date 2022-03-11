<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<title>Clients list</title>
<body>
<jsp:include page="../common/header.jsp"/>
<h2>All Clients</h2>
<br>

<table>
    <tr>
        <th>Client's name</th>
        <th>Client's surname</th>
        <th>Mobile number</th>
        <th>email</th>
        <th>Ready to work status</th>
        <th>Operations</th>

        <c:forEach var="clients" items="${allClients}">

        <c:url var="controlUpdateButton" value="/control/updateClient">
            <c:param name="clientId" value="${clients.id}"/>
        </c:url>

        <c:url var="deleteButton" value="/control/deleteClient">
            <c:param name="clientId" value="${clients.id}"/>
        </c:url>

        <c:url var="controlLockButton" value="/common/lockClient">
            <c:param name="clientId" value="${clients.id}"/>
        </c:url>

        <c:url var="controlUnlockButton" value="/common/unlockClient">
            <c:param name="clientId" value="${clients.id}"/>
        </c:url>

    <tr>
        <td>${clients.firstName}</td>
        <td>${clients.surname}</td>
        <td>${clients.phoneNumber}</td>
        <td>${clients.emailAddress}</td>
        <td>${clients.clientNumberReadyToWorkStatus}</td>
        <td>
            <security:authorize access="hasRole('control')">
                <input type="button" value="Update"
                    onclick="window.location.href = '${controlUpdateButton}'"/>
                <input type="button" value="Delete"
                    onclick="window.location.href = '${deleteButton}'"/>
                <c:choose>
                    <c:when test="${clients.clientNumberReadyToWorkStatus==true}">
                        <input type="button" value="Lock"
                               onclick="window.location.href = '${controlLockButton}'"/>
                    </c:when>
                    <c:otherwise>
                        <input type="button" value="Unlock"
                               onclick="window.location.href = '${controlUnlockButton}'"/>
                    </c:otherwise>
                </c:choose>
            </security:authorize>
        </td>
    </tr>

    </c:forEach>
</table>

<br>

<input type="button" value="Add"
       onclick="window.location.href = '/control/addNewClient'"/>
</body>
</html>