<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>
<title>Tariff full info</title>
<body>

<jsp:include page="../common/header.jsp"/>
<h2>Tariff info</h2>
<br>
<form:form action="/common/saveTariff" modelAttribute="tariffs">

    <form:hidden path="id"/>

    Name <form:input path="tariff_name" readonly="true"/>
    <br><br>
    Price <form:input path="price" readonly="true"/>
    <br><br>
    Available to connect <form:input path="availableToConnectOrNotStatus" readonly="true"/>
    <br><br>
    <input type="submit", value="Confirm"/>
</form:form>
</body>
</html>