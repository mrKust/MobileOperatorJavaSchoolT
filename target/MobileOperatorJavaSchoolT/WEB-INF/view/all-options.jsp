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
        <th>Operations</th>

        <c:forEach var="options" items="${allOptions}">

            <c:url var="updateOption" value="/updateOption">
                <c:param name="optionId" value="${options.id}"/>
            </c:url>
            <tr>
                <td>${options.getOptionsName()}</td>
                <td>${options.getPrice()}</td>
                <td>${options.getCostToAdd()}</td>
                <td>${options.isAvailableOptionToConnectOrNot()}</td>
                <td>
                    <input type="button" value="Update"
                        onclick="window.location.href = 'updateOption'"/>
                </td>
            </tr>
        </c:forEach>
    </tr>
</table>

<br>

<input type="button" value="Add"
    onclick="window.location.href = 'addNewOption'"/>
</body>
</html>
