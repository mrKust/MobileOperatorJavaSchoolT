<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>
<title>Add new option type</title>
<body>
<jsp:include page="../common/header.jsp"/>
<h2>New option type</h2>
<br>
<form:form action="/control/saveOptionType" modelAttribute="optionsType">

    <form:hidden path="id"/>

    Name <form:input path="optionType"/>
    <br><br>
    <input type="submit", value="Confirm"/>
</form:form>
</body>
</html>