<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet1" href="../style.css">
    <%--<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>--%>
    <%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
    --%><title>Update my info</title>
</head>

<body>

    <jsp:include page="../common/header.jsp"/>
    <h3>My info</h3>

    <c:url var="controlLockButton" value="/common/lockClient">
        <c:param name="clientId" value="${model.client.id}"/>
    </c:url>

    <c:url var="controlUnlockButton" value="/common/unlockClient">
        <c:param name="clientId" value="${model.client.id}"/>
    </c:url>

    <div class="container">
        <form:form action="/common/saveClient" modelAttribute="model">

            <form:hidden path="client.id"/>
            <form:hidden path="client.userRole"/>
            <form:hidden path="operationType"/>
            <form:hidden path="client.passwordLogIn"/>
            <form:hidden path="client.dateOfBirth"/>

            <div class="input-group">
                <span class="input-group-text">First and last name</span>
                <form:input aria-label="First name" class="form-control" path="client.firstName" readonly="true"/>
                <form:input aria-label="Last name" class="form-control" path="client.surname" readonly="true"/>
            </div>

            <div class="mb-3">
                <label for="birthDateString" class="form-label">Date of birth</label>
                <form:input class="form-control" type="text" path="birthDateString"/>
            </div>

            <div class="mb-3">
                <label for="client.passportNumber" class="form-label">Passport number</label>
                <form:input class="form-control" path="client.passportNumber"/>
            </div>

            <div class="mb-3">
                <label for="client.address" class="form-label">Home address</label>
                <form:input class="form-control" path="client.address"/>
            </div>

            <div class="mb-3">
                <label for="client.phoneNumber" class="form-label">Phone number</label>
                <form:input class="form-control" path="client.phoneNumber" readonly="true"/>
            </div>

            <div class="mb-3">
                <label for="client.emailAddress" class="form-label">E-mail</label>
                <form:input class="form-control" path="client.emailAddress" readonly="true"/>
            </div>

            <div class="input-group">
                <span class="input-group-text">Current user's available status & Who blocked number</span>
                <form:input class="form-control" path="client.clientNumberReadyToWorkStatus" readonly="true"/>
                <form:input class="form-control" path="client.roleOfUserWhoBlockedNumber" readonly="true"/>
            </div>

            <c:choose>
                <c:when test="${model.client.clientNumberReadyToWorkStatus==true}">
                    <button type="button" class="btn btn-warning"
                            onclick="window.location.href = '${controlLockButton}'">Lock</button>
                </c:when>
                <c:otherwise>
                    <c:if test="${model.client.roleOfUserWhoBlockedNumber ne 'control'}">
                        <button type="button" class="btn btn-warning"
                                onclick="window.location.href = '${controlUnlockButton}'">Unlock</button>
                    </c:if>
                </c:otherwise>
            </c:choose>

            <input type="submit" class="btn btn-primary" value="Confirm"/>

        </form:form>

    </div>

    <script type="text/javascript">
        id="selectedDateValue"
        $(function() {
            $.datepicker.setDefaults({
                changeMonth: true,
                changeYear: true,
                onSelect:function(date, inst){
                    $("#selectedDateValue").html(date);
                }
            });

            $( "#birthDateString" ).datepicker();
        });
    </script>
    <jsp:include page="../common/footer.jsp"/>

</body>
</html>