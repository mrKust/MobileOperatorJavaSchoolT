<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<form:form action="/common/saveClient" modelAttribute="client">

    <form:hidden path="id"/>
    <form:hidden path="userRole"/>
    <form:hidden path="password_log_in"/>
    <form:hidden path="roleOfUserWhoBlockedNumber"/>

    Name <form:input path="first_name"/>
    <br><br>
    Surname <form:input path="surname"/>
    <br><br>
    Date of birth: <form:input type="text" path="date_of_birth"/>
    <script>
        id="selectedDtaeVal"
        $(function() {
            $.datepicker.setDefaults({
                changeMonth: true,
                changeYear: true,
                onSelect:function(date, inst){
                    $("#selectedDtaeVal").html(date);
                }
            });

            $( "#date_of_birth" ).datepicker();
        });
    </script>
    <br><br>
    Passport_number <form:input path="passport_number"/>
    <br><br>
    Address <form:input path="address"/>
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
    <c:set var="role" value="null"/>
    <c:choose>
        <c:when test="${client.clientNumberBlockStatus} == false">
            <c:set var="role" value="control"/>
        </c:when>
    </c:choose>

    <br><br>
    <input type="submit", value="Confirm"/>
</form:form>
</body>
</html>