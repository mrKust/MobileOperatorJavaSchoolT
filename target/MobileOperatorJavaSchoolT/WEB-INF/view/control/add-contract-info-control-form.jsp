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

    <form:hidden path="object.id"/>

    <br><br>
    Client <form:select path="stringsClients">
    <c:forEach var="client" items="${clientsList}">
        <form:option value="${client.id}" label="${client.phone_number} ${client.surname} ${client.email_address}"/>
    </c:forEach>
</form:select>

    <br><br>
    Client's tariff <form:select path="stringsTariff">
    <c:forEach var="tariff" items="${tariffsList}">
        <form:option value="${tariff.id}" label="${tariff.tariff_name}"/>
    </c:forEach>
</form:select>

    <br><br>
    <input type="submit", value="Confirm"/>
</form:form>
</body>
</html>