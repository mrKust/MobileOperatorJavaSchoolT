<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="../style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <title>Update client's contract</title>
</head>

<body>
    <jsp:include page="../common/header.jsp"/>
    <h3>Client info</h3>

    <div class="container">
        <form:form action="/common/saveClient" modelAttribute="model">
            <form:hidden path="client.id"/>
            <form:hidden path="client.userRole"/>
            <form:hidden path="client.passwordLogIn"/>
            <form:hidden path="operationType"/>

            <div class="input-group">
                <span class="input-group-text">First and last name</span>
                <form:input aria-label="First name" class="form-control" path="client.firstName" readonly="true"/>
                <form:input aria-label="Last name" class="form-control" path="client.surname" readonly="true"/>
            </div>

            <div class="mb-3">
                <label for="client.dateOfBirth" class="form-label">Date of birth</label>
                <form:input class="form-control" type="text" path="client.dateOfBirth" readonly="true"/>
            </div>

            <div class="mb-3">
                <label for="client.passportNumber" class="form-label">Passport number</label>
                <form:input class="form-control" path="client.passportNumber" readonly="true"/>
            </div>

            <div class="mb-3">
                <label for="client.address" class="form-label">Home address</label>
                <form:input class="form-control" path="client.address" readonly="true"/>
            </div>

            <div class="mb-3">
                <label for="client.phoneNumber" class="form-label">Phone number</label>
                <form:input class="form-control" path="client.phoneNumber" readonly="true"/>
            </div>

            <div class="mb-3">
                <label for="client.emailAddress" class="form-label">E-mail</label>
                <form:input class="form-control" path="client.emailAddress" readonly="true"/>
            </div>

            <div class="mb-3">
                <label for="client.clientNumberReadyToWorkStatus" class="form-label">This client available</label>
                <form:input class="form-control" path="client.clientNumberReadyToWorkStatus" readonly="true"/>
            </div>

            <input type="submit" class="btn btn-primary" value="Confirm"/>

        </form:form>
    </div>

    <%--<form:form action="/common/saveClient" modelAttribute="model">

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
    </form:form>--%>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <jsp:include page="../common/footer.jsp"/>
</body>
</html>