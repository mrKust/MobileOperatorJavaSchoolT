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

            <hidden path="${model.tariff.id}"></hidden>

            <div class="mb-3">
                <label for="tariff.tariffName" class="form-label">Name</label>
                <input class="form-control" type="text" id="tariff.tariffName"
                       value="${model.tariff.tariffName}" readonly/>
            </div>

            <label for="tariff.price" class="form-label">Price</label>
            <div class="input-group mb-3">
                <span class="input-group-text">$</span>
                <span class="input-group-text">0.00</span>
                <input class="form-control" type="text" id="tariff.price"
                       value="${model.tariff.price}" readonly/>
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

            <div class="form-check form-switch">
                <label class="form-check-label">
                    This tariff available for connect
                </label>
                <c:if test="${model.tariff.availableToConnectOrNotStatus eq true}">
                    <input class="form-check-input" type="checkbox" role="switch"
                           id="tariff.availableTariffToConnectOrNot" checked disabled>
                </c:if>
                <c:if test="${model.tariff.availableToConnectOrNotStatus ne true}">
                    <input class="form-check-input" type="checkbox" role="switch"
                           id="tariff.availableTariffToConnectOrNot" disabled>
                </c:if>
            </div>

            <button type="button" class="btn btn-primary"
                    onclick="window.location.href = '/common/allTariffs'">Confirm</button>
        </div>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
</html>