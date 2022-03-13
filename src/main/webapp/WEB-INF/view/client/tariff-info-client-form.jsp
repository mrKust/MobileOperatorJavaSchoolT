<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<title>Tariff full info</title>
<body>
<jsp:include page="../common/header.jsp"/>
<h2>Tariff info</h2>
<br>
<form:form action="/common/allTariffs" modelAttribute="model">

    <form:hidden path="tariff.id"/>
    <form:hidden path="operationType"/>

    Name <form:input path="tariff.tariffName" readonly="true"/>
    <br><br>
    Price <form:input path="tariff.price" readonly="true"/>
    <br><br>
    Available options for this tariff
    <table>
        <c:forEach var="option" items="${optionsList}">
            <c:if test="${option.availableOptionToConnectOrNot==true}">
                <tr id=${option.id}><td>${option.optionsName}</td></tr>
            </c:if>
        </c:forEach>
    </table>
    <br><br>
    This tariff available for connect <form:input path="tariff.availableToConnectOrNotStatus" readonly="true"/>
    <br><br>
    <input type="submit", value="Confirm"/>
</form:form>
</body>
</html>