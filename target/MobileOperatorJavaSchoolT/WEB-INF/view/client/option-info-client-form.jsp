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

    Name <form:input path="options.optionsName" readonly="true"/>
    <br><br>
    Price <form:input path="options.price" readonly="true"/>
    <br><br>
    Cost to add <form:input path="options.costToAdd" readonly="true"/>
    <br><br>
    Option category <form:input path="options.optionType.optionType" readonly="true"/>
    <br><br>
    Available to connect <form:input path="options.availableOptionToConnectOrNot" readonly="true"/>
    <br><br>
    <input type="submit", value="Confirm"/>
</form:form>
</body>
</html>