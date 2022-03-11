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
    <form:form action="/common/saveClient" modelAttribute="model">

        <form:hidden path="client.id"/>
        <form:hidden path="client.userRole"/>
        <form:hidden path="client.passwordLogIn"/>
        <form:hidden path="operationType"/>

        Name <form:input path="client.firstName" readonly="true"/>
        <br><br>
        Surname <form:input path="client.surname" readonly="true"/>
        <br><br>
        Date of birth: <form:input type="text" path="client.dateOfBirth" readonly="true"/>
        <br><br>
        Passport_number <form:input path="client.passportNumber" readonly="true"/>
        <br><br>
        Address <form:input path="client.address" readonly="true"/>
        <br><br>
        Phone number <form:input path="client.phoneNumber" readonly="true"/>
        <br><br>
        email <form:input path="client.emailAddress" readonly="true"/>
        <br><br>
        Ready to work status <form:input path="client.clientNumberReadyToWorkStatus" readonly="true"/>
        Role of user who blocked number <form:input path="client.roleOfUserWhoBlockedNumber" readonly="true"/>
        <br><br>
        <input type="submit", value="Confirm"/>
    </form:form>
</body>
</html>