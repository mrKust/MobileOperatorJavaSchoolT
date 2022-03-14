<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>
<title>Add new option</title>
<body>
<jsp:include page="../common/header.jsp"/>
<h2>Option info</h2>
<br>
<form:form action="/common/saveOption" modelAttribute="model">

    <form:hidden path="options.id"/>
    <form:hidden path="options.optionType.id"/>

    Name <form:input path="options.optionsName"/>
    <br><br>
    Price <form:input path="options.price"/>
    <br><br>
    Cost to add <form:input path="options.costToAdd"/>
    <br><br>
    Option category <form:input path="options.optionType.optionType" readonly="true"/>
    <br><br>
    Status <form:select path="options.availableOptionToConnectOrNot">
    <form:option value="true" label="Ready to connect"/>
    <form:option value="false" label="Couldn't be connected"/>
</form:select>
    <br><br>
    <input type="submit", value="Confirm"/>
</form:form>

<jsp:include page="../common/footer.jsp"/>
</body>
</html>