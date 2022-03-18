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
    <title>Add new tariff</title>
</head>

<body>
    <jsp:include page="../common/header.jsp"/>
    <h3>Add tariff info</h3>

    <div class="container">

        <jsp:include page="../common/error-text.jsp">
            <jsp:param name="errorMessage" value="${errorMessage}"/>
        </jsp:include>

        <form:form action="/common/saveTariff" modelAttribute="model">

            <form:hidden path="tariff.id"/>
            <form:hidden path="operationType"/>

            <div class="mb-3">
                <label for="tariff.tariffName" class="form-label">Name</label>
                <form:input class="form-control" type="text" path="tariff.tariffName" placeholder="input tariff name here"/>
            </div>

            <div class="mb-3">
                <label for="tariff.price" class="form-label">Price</label>
                <form:input class="form-control" type="number" min="0" path="tariff.price" placeholder="input tariff price here"/>
            </div>

            <c:forEach var="option" items="${optionsList}">
                <c:if test="${option.availableOptionToConnectOrNot==true}">
                    <div class="form-check">
                        <lable class="form-check-label">
                            ${option.optionType.optionType} ${option.optionsName}
                        </lable>
                        <form:checkbox class="form-check-input" path="stringsOptions" value="${option.id}" name="list"/>
                    </div>
                </c:if>
            </c:forEach>

            <div class="form-check form-switch">
                <label class="form-check-label">
                    This tariff available to connect
                </label>
                <form:checkbox class="form-check-input" path="tariff.availableToConnectOrNotStatus"/>
            </div>

            <input type="submit" class="btn btn-primary" value="Confirm">

        </form:form>
    </div>

    <jsp:include page="../common/footer.jsp"/>
</body>
</html>