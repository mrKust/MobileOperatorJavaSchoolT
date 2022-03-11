<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
</head>

<title>Add new client</title>

<body>

    <c:url var="controlLockButton" value="/common/lockClient">
        <c:param name="clientId" value="${clients.id}"/>
    </c:url>

    <c:url var="controlUnlockButton" value="/common/unlockClient">
        <c:param name="clientId" value="${clients.id}"/>
    </c:url>

<jsp:include page="../common/header.jsp"/>
    <h2>Client info</h2>
    <br>
    <form:form action="/common/saveClient" modelAttribute="model">

        <form:hidden path="client.id"/>
        <form:hidden path="client.userRole"/>
        <form:hidden path="client.passwordLogIn"/>
        <form:hidden path="operationType"/>

        Name <form:input path="client.firstName"/>
        <br><br>
        Surname <form:input path="client.surname"/>
        <br><br>
        Date of birth: <form:input type="text" path="client.dateOfBirth"/>
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
        Passport_number <form:input path="client.passportNumber"/>
        <br><br>
        Address <form:input path="client.address"/>
        <br><br>
        Phone number <form:input path="client.phoneNumber" readonly="true"/>
        <br><br>
        email <form:input path="client.emailAddress" readonly="true"/>
        <br><br>
        Ready to work status <form:input path="client.clientNumberReadyToWorkStatus" readonly="true"/>
        Role of the user who block number <form:input path="client.roleOfUserWhoBlockedNumber" readonly="true"/>
        <br><br>
        <c:choose>
            <c:when test="${clients.clientNumberReadyToWorkStatus==true}">
                <input type="button" value="Lock"
                       onclick="window.location.href = '${controlLockButton}'"/>
            </c:when>
            <c:otherwise>
                <c:if test="${clients.roleOfUserWhoBlockedNumber ne 'control'}">
                    <input type="button" value="Unlock"
                           onclick="window.location.href = '${controlUnlockButton}'"/>
                </c:if>
            </c:otherwise>
        </c:choose>

        <br><br>
        <input type="submit", value="Confirm"/>
    </form:form>
</body>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</html>