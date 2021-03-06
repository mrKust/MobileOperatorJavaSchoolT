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
    <title>Option info</title>
</head>

<body>
    <jsp:include page="../common/header.jsp"/>

    <main>
        <div class="container">
            <h3>Option info</h3>

                <hidden path="${model.options.id}"></hidden>
                <hidden path="${model.options.optionType.id}"></hidden>

                <div class="mb-3">
                    <label for="name" class="form-label">Name</label>
                    <input class="form-control" type="text" id="name" value="${model.options.optionsName}" readonly/>
                </div>

                <label for="price" class="form-label">Price</label>
                <div class="input-group mb-3">
                    <span class="input-group-text">$</span>
                    <span class="input-group-text">0.00</span>
                    <input class="form-control" type="number" min="0" id="price" value="${model.options.price}"
                                readonly/>
                </div>

                <label for="options.costToAdd" class="form-label">Cost to connect</label>
                <div class="input-group mb-3">
                    <span class="input-group-text">$</span>
                    <span class="input-group-text">0.00</span>
                    <input class="form-control" type="number" min="0" id="options.costToAdd"
                           value="${model.options.costToAdd}" readonly/>
                </div>

                <div class="mb-3">
                    <label for="options.optionType.optionType" class="form-label">Price</label>
                    <input class="form-control" type="text" id="options.optionType.optionType"
                           value="${model.options.optionType.optionType}" readonly/>
                </div>

                <div class="form-check form-switch">
                    <label class="form-check-label">
                        This option available to connect
                    </label>
                    <c:if test="${model.options.availableOptionToConnectOrNot eq true}">
                        <input class="form-check-input" type="checkbox" role="switch"
                               id="options.availableOptionToConnectOrNot" checked disabled>
                    </c:if>
                    <c:if test="${model.options.availableOptionToConnectOrNot ne true}">
                        <input class="form-check-input" type="checkbox" role="switch"
                               id="options.availableOptionToConnectOrNot" disabled>
                    </c:if>
                </div>
                <button type="button" class="btn btn-primary"
                        onclick="window.location.href = '/common/allOptions'">Confirm</button>
        </div>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
</html>