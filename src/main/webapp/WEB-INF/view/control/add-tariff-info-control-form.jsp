<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<title>Tariff full info</title>
<body>
<jsp:include page="../common/header.jsp"/>
<h2>Tariff info</h2>
<br>
<form:form action="/common/saveTariff" modelAttribute="model">

    <form:hidden path="tariff.id"/>
    <form:hidden path="operationType"/>

    Name <form:input path="tariff.tariffName"/>
    <br><br>
    Price <form:input path="tariff.price"/>
    <br><br>
    <table>
        <c:forEach var="option" items="${optionsList}">
            <c:if test="${option.availableOptionToConnectOrNot==true}">
                <tr id=${option.id}><td>${option.optionsName}</td><td><form:checkbox path="stringsOptions" value="${option.id}" name="list"/></td>
                </tr>
            </c:if>
        </c:forEach>
    </table>

    <br>
    This tariff <form:select path="tariff.availableToConnectOrNotStatus">
    <form:option value="true" label="Ready to connect"/>
    <form:option value="false" label="Couldn't be connected"/>
</form:select>
    <br><br>
    <input type="submit", value="Confirm"/>
</form:form>
</body>
</html>