<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>

<html>
<head>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>

<title>Add new client</title>

<body>
<jsp:include page="../common/header.jsp"/>
    <h2>Client info</h2>
    <br>
    <form:form action="/common/saveClient" modelAttribute="clients">

        <form:hidden path="id"/>
        <form:hidden path="userRole"/>
        <form:hidden path="password_log_in"/>

        Name <form:input path="first_name" readonly="true"/>
        <br><br>
        Surname <form:input path="surname" readonly="true"/>
        <br><br>
        Date of birth: <form:input type="text" path="date_of_birth" readonly="true"/>
        <br><br>
        Passport_number <form:input path="passport_number" readonly="true"/>
        <br><br>
        Address <form:input path="address" readonly="true"/>
        <br><br>
        Phone number <form:input path="phone_number" readonly="true"/>
        <br><br>
        email <form:input path="email_address" readonly="true"/>
        <br><br>
        Block status <form:select path="clientNumberBlockStatus">
        <form:option value="true" label="Ready to work"/>
        <form:option value="false" label="Couldn't work. It's blocked"/>
        </form:select>
        Role of user who blocked number <form:input path="roleOfUserWhoBlockedNumber" readonly="true"/>
        <br><br>
        <input type="submit", value="Confirm"/>
    </form:form>
</body>
</html>