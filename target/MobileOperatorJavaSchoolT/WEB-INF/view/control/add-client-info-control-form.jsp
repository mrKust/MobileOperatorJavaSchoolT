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
    <title>Add new client</title>
</head>

<body>
    <jsp:include page="../common/header.jsp"/>

    <main>
        <div class="container">
            <h3>Add client info</h3>

            <c:if test="${errorMessage ne null}">
                <jsp:include page="../common/error-text.jsp">
                    <jsp:param name="errorMessage" value="${errorMessage}"/>
                </jsp:include>
            </c:if>

            <c:if test="${successMessage ne null}">
                <jsp:include page="../common/success-text.jsp">
                    <jsp:param name="successMessage" value="${successMessage}"/>
                </jsp:include>
            </c:if>

            <form:form action="/common/saveClient" modelAttribute="model">

                <form:hidden path="client.id"/>

                <div class="input-group">
                    <span class="input-group-text">First and last name</span>
                    <form:input aria-label="First name" type="text" required="true" class="form-control" path="client.firstName" placeholder="input First name here"/>
                    <form:input aria-label="Last name" type="text" required="true" class="form-control" path="client.surname" placeholder="input Second name here"/>
                </div>

                <div class="mb-3">
                    <label for="client.dateOfBirth" class="form-label">Date of birth</label>
                    <form:input id="datefield" type="date" max="" data-date-end-date="0d" class="form-control" path="client.dateOfBirth" />
                    <script>
                        var today = new Date();
                        var dd = today.getDate();
                        var mm = today.getMonth() + 1;
                        var yyyy = today.getFullYear();

                        if (dd < 10) {
                            dd = '0' + dd;
                        }

                        if (mm < 10) {
                            mm = '0' + mm;
                        }

                        today = yyyy + '-' + mm + '-' + dd;
                        document.getElementById("datefield").setAttribute("max", today)
                    </script>
                </div>

                <div class="mb-3">
                    <label for="client.passportNumber" class="form-label">Passport number</label>
                    <form:input class="form-control" required="true" type="text" path="client.passportNumber"/>
                </div>

                <div class="mb-3">
                    <label for="client.address" class="form-label">Home address</label>
                    <form:input class="form-control" required="true" type="text" path="client.address"/>
                </div>

                <div class="mb-3">
                    <label for="client.emailAddress" class="form-label">Email address</label>
                    <form:input type="email" required="true" class="form-control" path="client.emailAddress"
                                autocomplete="new-email"/>
                </div>

                <div class="mb-3">
                    <label class="form-label">Select role</label>
                    <form:select class="form-select" path="client.userRole">
                        <form:option value="client" label="Client"/>
                        <form:option value="control" label="Control"/>
                    </form:select>
                </div>

                <input type="submit" class="btn btn-primary" value="Confirm">

            </form:form>
        </div>
    </main>

    <jsp:include page="../common/footer.jsp"/>
</body>
</html>