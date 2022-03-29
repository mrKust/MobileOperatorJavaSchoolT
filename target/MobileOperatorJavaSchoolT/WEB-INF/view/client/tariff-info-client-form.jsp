<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/resources/css/style.css">
    <title>Tariff full info</title>
</head>

<body>
    <jsp:include page="../common/header.jsp"/>

    <main>
        <div class="container">
            <h3>Tariff info</h3>

            <c:if test="${errorMessage ne null}">
                <jsp:include page="../common/error-text.jsp">
                    <jsp:param name="errorMessage" value="${errorMessage}"/>
                </jsp:include>
            </c:if>

            <form:form action="/common/allTariffs" modelAttribute="model">

                <form:hidden path="tariff.id"/>
                <form:hidden path="operationType"/>

                <div class="mb-3">
                    <label for="tariff.tariffName" class="form-label">Name</label>
                    <form:input class="form-control" type="text" path="tariff.tariffName" readonly="true"/>
                </div>

                <label for="tariff.price" class="form-label">Price</label>
                <div class="input-group mb-3">
                    <span class="input-group-text">$</span>
                    <span class="input-group-text">0.00</span>
                    <form:input class="form-control" type="text" path="tariff.price" readonly="true"/>
                </div>

                <table class="table">
                    <label class="form-label">Available options</label>
                    <thead>
                    <th scope="col">Category</th>
                    <th scope="col">Name</th>
                    </thead>
                    <tbody>
                    <c:forEach var="option" items="${optionsList}">
                        <c:if test="${option.availableOptionToConnectOrNot==true}">
                            <tr>
                                <td>${option.optionType.optionType}</td>
                                <td>${option.optionsName}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>

                <div class="mb-3">
                    <label for="tariff.price" class="form-label">This tariff available for connect</label>
                    <c:if test="${model.tariff.availableToConnectOrNotStatus eq true}">
                        <form:checkbox class="form-check-input" path="tariff.availableToConnectOrNotStatus" checked="checked" readonly="true"/>
                    </c:if>
                    <c:if test="${model.tariff.availableToConnectOrNotStatus ne true}">
                        <form:checkbox class="form-check-input" path="tariff.availableToConnectOrNotStatus" readonly="true"/>
                    </c:if>
                </div>

                <input type="submit" class="btn btn-primary" value="Confirm">
            </form:form>
        </div>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
</html>