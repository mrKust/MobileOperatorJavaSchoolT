<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../css/style.css">
    <title>Add new option type</title>
</head>

<body>
    <jsp:include page="../common/header.jsp"/>
    <h3>New phone number</h3>

    <div class="container">

        <jsp:include page="../common/error-text.jsp">
            <jsp:param name="errorMessage" value="${errorMessage}"/>
        </jsp:include>

        <form:form action="/control/savePhoneNumber" modelAttribute="phoneNumber">

            <form:hidden path="id"/>
            <form:hidden path="availableToConnectStatus"/>

            <div class="mb-3">
                <label for="phoneNumber" class="form-label">Number</label>
                <form:input class="form-control" type="tel" path="phoneNumber" list="numbers"
                        placeholder="Phone number: 8XXXXXXXXXX" pattern="[0-9]{11}"/>
            </div>

            <input type="submit" class="btn btn-primary" value="Confirm"/>
         </form:form>

        <table class="table table-striped">
            <thead>
            <th scope="col">Phone number</th>
            <th scope="col">Available status</th>
            </thead>
            <tbody>
            <c:forEach var="number" items="${allNumbers}">

                <tr>
                    <th scope="row">${number.phoneNumber}</th>
                    <td>${number.availableToConnectStatus}</td>
                </tr>

            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="progress">
        <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 75%"></div>
    </div>
    <jsp:include page="../common/footer.jsp"/>
</body>
</html>