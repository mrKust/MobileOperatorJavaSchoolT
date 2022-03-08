<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>
<title>Add new option</title>
<body>

<jsp:include page="../common/header.jsp"/>
<h2>Option info</h2>
<br>
<form:form action="/common/saveOption" modelAttribute="options">

    <form:hidden path="id"/>

     Name <form:input path="optionsName" readonly="true"/>
    <br><br>
     Price <form:input path="price" readonly="true"/>
    <br><br>
     Cost to add <form:input path="costToAdd" readonly="true"/>
    <br><br>
     Available to connect <form:input path="availableOptionToConnectOrNot" readonly="true"/>
    <br><br>
    <input type="submit", value="Confirm"/>
</form:form>
</body>
</html>