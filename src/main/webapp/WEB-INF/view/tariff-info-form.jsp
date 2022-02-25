<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>
<title>Tariff full info</title>
<body>
<h2>Tariff info</h2>
<br>
<form:form action="infoTariff" modelAttribute="tariffs">

    <form:hidden path="id"/>

    Name <form:input path="optionsName"/>
    <br><br>
    Price <form:input path="price"/>
    <br><br>
    Cost to add <form:input path="costToAdd"/>
    <br><br>
    Status <form:select path="availableOptionToConnectOrNot">
    <form:option value="true" label="Ready to connect"/>
    <form:option value="false" label="Couldn't be connected"/>
</form:select>
    <br><br>
    <input type="submit", value="Confirm"/>
</form:form>
</body>
</html>