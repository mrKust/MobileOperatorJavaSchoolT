<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>
<title>Add new contract</title>
<body>
<h2>Contract info</h2>
<br>
<form:form action="/common/saveContract" modelAttribute="contracts">

    <form:hidden path="id"/>

    <br><br>
    <input type="submit", value="Confirm"/>
</form:form>
</body>
</html>