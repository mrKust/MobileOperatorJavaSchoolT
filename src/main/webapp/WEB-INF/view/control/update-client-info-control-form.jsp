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
    <link rel="stylesheet" href="/resources/css/style.css">
    <title>Update client's contract</title>
</head>

<body>
    <jsp:include page="../common/header.jsp"/>

    <main>
        <div class="container">

            <h3>Client info</h3>
            <c:if test="${errorMessage ne null}">
                <jsp:include page="../common/error-text.jsp">
                    <jsp:param name="errorMessage" value="${errorMessage}"/>
                </jsp:include>
            </c:if>

            <form:form action="/common/saveClient" modelAttribute="model">
                <form:hidden path="client.id"/>
                <form:hidden path="client.userRole"/>
                <form:hidden path="operationType"/>

                <div class="input-group">
                    <span class="input-group-text">First and last name</span>
                    <form:input aria-label="First name" type="text" class="form-control" path="client.firstName" readonly="true"/>
                    <form:input aria-label="Last name" type="text" class="form-control" path="client.surname" readonly="true"/>
                </div>

                <div class="mb-3">
                    <label for="client.dateOfBirth" class="form-label">Date of birth</label>
                    <form:input class="form-control" type="text" path="client.dateOfBirth" readonly="true"/>
                </div>

                <div class="mb-3">
                    <label for="client.passportNumber" class="form-label">Passport number</label>
                    <form:input class="form-control" type="text" path="client.passportNumber" readonly="true"/>
                </div>

                <div class="mb-3">
                    <label for="client.address" class="form-label">Home address</label>
                    <form:input class="form-control" type="text" path="client.address" readonly="true"/>
                </div>

                <div class="mb-3">
                    <label for="client.phoneNumber" class="form-label">Phone number</label>
                    <form:input class="form-control" type="tel" path="client.phoneNumber" readonly="true"
                                pattern="[0-9]{11}"/>
                </div>

                <div class="mb-3">
                    <label for="client.emailAddress" class="form-label">E-mail</label>
                    <form:input class="form-control" type="email" path="client.emailAddress" readonly="true"/>
                </div>

                <div class="mb-3">
                    <label for="client.contract.contractTariff.tariffName" class="form-label">Current connected tariff</label>
                    <form:input class="form-control" type="text" path="client.contract.contractTariff.tariffName" readonly="true"/>
                </div>

                <div class="mb-3">
                    <label class="form-check-label">Connected options</label>
                </div>
                <c:forEach var="option" items="${model.client.contract.connectedOptions}">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="connectedOption" disabled>
                        <label class="form-check-label" for="connectedOption">
                                ${option.optionType.optionType} ${option.optionsName}
                        </label>
                        <form:checkbox class="form-check-input" path="passwordString" value="${option.id}" name="list" checked="checked" disabled="true"/>
                    </div>
                </c:forEach>

                <div class="mb-3">
                    <label for="client.clientNumberReadyToWorkStatus" class="form-label">This client available</label>
                    <form:input class="form-control" type="text" path="client.clientNumberReadyToWorkStatus" readonly="true"/>
                </div>

                <input type="submit" class="btn btn-primary" value="Confirm"/>

            </form:form>
        </div>
    </main>

    <jsp:include page="../common/footer.jsp"/>
</body>
</html>