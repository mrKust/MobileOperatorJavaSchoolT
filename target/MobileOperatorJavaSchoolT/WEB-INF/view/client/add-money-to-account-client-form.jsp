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
    <title>Add money</title>
</head>

<body>

<jsp:include page="../common/header.jsp"/>

<main>
    <div class="container">
        <h3>Create new password</h3>

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

        <form:form action="/client/moneyProcess" modelAttribute="model">

            <form:hidden path="client.id"/>
            <form:hidden path="client.userRole"/>
            <form:hidden path="client.firstName"/>
            <form:hidden path="client.surname"/>
            <form:hidden path="client.dateOfBirth"/>
            <form:hidden path="client.passportNumber"/>
            <form:hidden path="client.address"/>
            <form:hidden path="client.emailAddress"/>
            <form:hidden path="client.moneyBalance"/>


            <div class="mb-3">
                <label for="client.passportNumber" class="form-label">Money sum </label>
                <form:input class="form-control" type="number" required="true" min="0"
                            max="9999999" path="money"
                            placeholde="input amount of money here"/>
            </div>

            <div class="mb-3">
                <label for="client.passportNumber" class="form-label">Card number</label>
                <input class="form-control" type="text" required="true" pattern="[0-9]{16}"
                            id="cardNumber" placeholde="input card number without spaces"/>
            </div>

            <div class="mb-3">
                <label for="client.dateOfBirth" class="form-label">Date of expires</label>
                <input id="datefield" type="date" max="" required="true" data-date-end-date="0d" class="form-control" path="" />
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
                    document.getElementById("datefield").setAttribute("min", today)
                </script>
            </div>

            <div class="input-group">
                <span class="input-group-text">Input surname and name</span>
                <input type="text" aria-label="Surname" class="form-control"
                            id="firstName" required="true" placeholder="Input surname here"/>
                <input type="text" aria-label="First name" class="form-control"
                            id="surname" required="true" placeholder="Input name here"/>
            </div>

            <div class="mb-3">
                <label for="client.passportNumber" class="form-label">CVC</label>
                <input class="form-control" type="number" min="100" max="999"
                            id="cvc" required="true" placeholder="input cvc numper"/>
            </div>


            <input type="submit" class="btn btn-primary" value="Confirm"/>

        </form:form>
    </div>
</main>

<jsp:include page="../common/footer.jsp"/>

</body>
</html>