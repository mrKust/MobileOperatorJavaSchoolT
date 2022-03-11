<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<title>Add new contract</title>
<body>
<jsp:include page="../common/header.jsp"/>
<h2>Contract info</h2>
<br>
<form:form action="/common/saveContract" modelAttribute="model">

    <form:hidden path="contract.id"/>
    <form:hidden path="operationType"/>

    <br><br>
    Client <select name="stringsClients">
    <c:forEach var="client" items="${clientsList}">
        <option value="${client.id}" label="${client.phoneNumber} ${client.surname} ${client.emailAddress}">
    </c:forEach>
</select>

    <br><br>
    Client's tariff <select name="stringsTariff">
    <c:forEach var="tariff" items="${tariffsList}">
        <c:if test="${tariff.availableToConnectOrNotStatus eq true}">
        <option value="${tariff.id}" label="${tariff.tariffName}">
        </c:if>

    </c:forEach>
</select>

    <br><br>
    <input type="submit", value="Confirm"/>
</form:form>
</body>
</html>