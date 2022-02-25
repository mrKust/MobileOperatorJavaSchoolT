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
        <th>List of available options</th>

        <c:forEach var="tariffs" items="${allTariffs}">
            <tr>
                <td>${tariffs.tariff_name}</td>
                <td>${tariffs.price}</td>
                <td>${tariffs.availableToConnectOrNotStatus}</td>
            </tr>
        </c:forEach>
    </tr>
</table>
</body>
</html>