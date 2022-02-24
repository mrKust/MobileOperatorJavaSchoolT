<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<title>Options List</title>
<body>
<h2>All Options</h2>
<br>

<table>
    <tr>
        <th>Option's name</th>
        <th>Price</th>
        <th>Cost to add</th>
        <th>Available to Connect</th>

        <c:forEach var="options" items="${allOptions}">
            <tr>
                <td>${options.getOptionsName()}</td>
                <td>${options.getPrice()}</td>
                <td>${options.getCostToAdd()}</td>
                <td>${options.isAvailableOptionToConnectOrNot()}</td>
            </tr>
        </c:forEach>
    </tr>
</table>
</body>
</html>
