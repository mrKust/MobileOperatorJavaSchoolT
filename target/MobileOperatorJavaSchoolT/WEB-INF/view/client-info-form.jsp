<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>
<title>Add new client</title>
<body>
<h2>Client info</h2>
<br>
<form:form action="saveClient" modelAttribute="clients">

    <form:hidden path="id"/>

    Name <form:input path="first_name"/>
    <br><br>
    Surname <form:input path="surname"/>
    <br><br>
    Phone number <form:input path="phone_number"/>
    <br><br>
    email <form:input path="email_address"/>
    <br><br>
    Block status <form:select path="clientNumberBlockStatus">
    <form:option value="true" label="Ready to work"/>
    <form:option value="false" label="Couldn't work. It's blocked"/>
</form:select>
    <br><br>
    <input type="submit", value="Confirm"/>
</form:form>
</body>
</html>