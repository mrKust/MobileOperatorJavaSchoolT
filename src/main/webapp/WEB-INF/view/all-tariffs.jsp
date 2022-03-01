<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

            <%--<c:url var="infoButton" value="/tariffInfo">
                <c:param name="tariffId" value="${tariffs.id}"/>
            </c:url>--%>
        <c:url var="updateButton" value="/updateTariff">
            <c:param name="tariffId" value="${tariffs.id}"/>
        </c:url>

        <c:url var="deleteButton" value="/deleteTariff">
            <c:param name="tariffId" value="${tariffs.id}"/>
        </c:url>

            <tr>
                <td>${tariffs.tariff_name}</td>
                <td>${tariffs.price}</td>
                <td>${tariffs.availableToConnectOrNotStatus}</td>
                <td>
                    <input type="button" value="Show more"
                        onclick="window.location.href = '${infoButton}'"/>
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
       onclick="window.location.href = 'addNewTariff'"/>
</body>
</html>