<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<title>Options type list</title>
<body>
<jsp:include page="../common/header.jsp"/>
<h2>All options types</h2>
<br>

<table>
    <tr>
        <th>Option category's id</th>

        <c:forEach var="optionsType" items="${allOptionsType}">

        <c:url var="deleteButton" value="/control/deleteOptionType">
            <c:param name="optionsTypeId" value="${optionsType.id}"/>
        </c:url>

    <tr>
        <td>${optionsType.id}</td>
        <td>${optionsType.optionType}</td>
        <td>
            <input type="button" value="Delete"
                   onclick="window.location.href = '${deleteButton}'"/>
        </td>
    </tr>

    </c:forEach>
    </tr>
</table>

<br>

<input type="button" value="Add"
       onclick="window.location.href = '/control/addNewOptionCategory'"/>

<jsp:include page="../common/footer.jsp"/>
</body>
</html>
