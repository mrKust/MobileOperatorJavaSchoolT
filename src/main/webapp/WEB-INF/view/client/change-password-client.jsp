<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link rel="stylesheet1" href="../css/style.css">
    <title>Change password</title>
</head>

<body>

    <jsp:include page="../common/header.jsp"/>
    <h3>Create new password</h3>

    <div class="container">

        <jsp:include page="../common/error-text.jsp">
            <jsp:param name="errorMessage" value="${errorMessage}"/>
        </jsp:include>

        <form:form action="/common/saveClient" modelAttribute="model">

            <form:hidden path="client.id"/>
            <form:hidden path="client.userRole"/>
            <form:hidden path="client.firstName"/>
            <form:hidden path="client.surname"/>
            <form:hidden path="client.dateOfBirth"/>
            <form:hidden path="client.passportNumber"/>
            <form:hidden path="client.address"/>
            <form:hidden path="client.phoneNumber"/>
            <form:hidden path="client.emailAddress"/>
            <form:hidden path="client.clientNumberReadyToWorkStatus"/>
            <form:hidden path="operationType"/>


            <div class="input-group">
                <span class="input-group-text">Input new password and repeat it</span>
                <form:input type="password" aria-label="Password" class="form-control"
                            path="passwordString" aria-described="passwordHelper"
                            placeholder="Input new password here"/>
                <form:input type="password" aria-label="Repeat password" class="form-control"
                            path="passwordString2" aria-described="passwordHelper"
                            placeholder="Repeat new password here"/>
            </div>
            <div class="col-auto">
                    <span id="passwordHelp" class="form-text">
                        Must be less than 255 characters long.
                    </span>
            </div>


        <input type="submit" class="btn btn-primary" value="Confirm"/>

    </form:form>

</div>

<jsp:include page="../common/footer.jsp"/>

</body>
</html>