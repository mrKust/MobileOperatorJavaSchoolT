<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
        <security:authorize access="hasRole('control')">
            <th>Operations</th>
        </security:authorize>

        <c:forEach var="options" items="${allOptions}">

            <c:url var="controlUpdateButton" value="/control/updateOption">
                <c:param name="optionId" value="${options.id}"/>
            </c:url>

            <c:url var="deleteButton" value="/control/deleteOption">
                <c:param name="optionId" value="${options.id}"/>
            </c:url>

            <c:url var="clientUpdateButton" value="/client/updateOption">
                <c:param name="optionId" value="${options.id}"/>
            </c:url>

            <tr>
                <td>${options.optionsName}</td>
                <td>${options.price}</td>
                <td>${options.costToAdd}</td>
                <td>${options.availableOptionToConnectOrNot}</td>
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
    onclick="window.location.href = '/control/addNewOption'"/>
</security:authorize>

</body>
</html>
