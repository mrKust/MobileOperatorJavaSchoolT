<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>

<html>
<title>List of tariffs</title>
<body>
<h2>All tariffs</h2>
<br>
<table>
    <tr>
        <th>Tariff's name</th>
        <th>Price</th>
        <th>Available to Connect</th>
        <th>External information</th>

        <c:forEach var="tariffs" items="${allTariffs}">

        <c:url var="clientUpdateButton" value="/client/updateTariff">
            <c:param name="tariffId" value="${tariffs.id}"/>
        </c:url>

        <c:url var="controlUpdateButton" value="/control/updateTariff">
            <c:param name="tariffId" value="${tariffs.id}"/>
        </c:url>

        <c:url var="deleteButton" value="/control/deleteTariff">
            <c:param name="tariffId" value="${tariffs.id}"/>
        </c:url>

            <tr>
                <td>${tariffs.tariff_name}</td>
                <td>${tariffs.price}</td>
                <td>${tariffs.availableToConnectOrNotStatus}</td>
                <td>
                    <security:authorize access="hasRole('control')">
                    <input type="button" value="Update"
                           onclick="window.location.href = '${controlUpdateButton}'"/>
                    <input type="button" value="Delete"
                           onclick="window.location.href = '${deleteButton}'"/>
                    </security:authorize>

                    <security:authorize access="hasRole('client')">
                    <input type="button" value="Show external info"
                           onclick="window.location.href = '${clientUpdateButton}'"/>
                    </security:authorize>
                </td>
            </tr>
        </c:forEach>
    </tr>
</table>

<br>
<security:authorize access="hasRole('control')">
    <input type="button" value="Add"
           onclick="window.location.href = '/control/addNewTariff'"/>
</security:authorize>

</body>
</html>