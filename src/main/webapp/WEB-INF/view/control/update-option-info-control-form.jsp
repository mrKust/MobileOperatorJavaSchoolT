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
    <title>Update option</title>
</head>

<body>
    <jsp:include page="../common/header.jsp"/>

    <main>
        <div class="container">

            <h3>Option info</h3>
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

            <form:form action="/control/patchOption" modelAttribute="model">

                <form:hidden path="options.id"/>
                <form:hidden path="options.optionType.id"/>

                <div class="mb-3">
                    <label for="options.optionsName" class="form-label">Name</label>
                    <form:input class="form-control" type="text" required="true" path="options.optionsName" placeholder="input option's name here"/>
                </div>

                <label for="options.price" class="form-label">Price</label>
                <div class="input-group mb-3">
                    <span class="input-group-text">$</span>
                    <span class="input-group-text">0.00</span>
                    <form:input class="form-control" type="number" min="0" step="0.01" path="options.price" placeholder="input option's price here"/>
                </div>

                <label for="options.costToAdd" class="form-label">Cost to connect</label>
                <div class="input-group mb-3">
                    <span class="input-group-text">$</span>
                    <span class="input-group-text">0.00</span>
                    <form:input class="form-control" type="number" min="0" step="0.01" path="options.costToAdd" placeholder="input option's cost to connect here"/>
                </div>

                <div class="mb-3">
                    <label for="options.optionType.optionType" class="form-label">Category</label>
                    <form:input class="form-control" type="text" path="options.optionType.optionType" readonly="true"/>
                </div>

                <div class="form-check form-switch">
                    <label class="form-check-label">
                        This option available to connect
                    </label>
                    <c:if test="${model.options.availableOptionToConnectOrNot eq true}">
                        <form:checkbox class="form-check-input" path="options.availableOptionToConnectOrNot" checked="checked"/>
                    </c:if>
                    <c:if test="${model.options.availableOptionToConnectOrNot ne true}">
                        <form:checkbox class="form-check-input" path="options.availableOptionToConnectOrNot"/>
                    </c:if>
                </div>
                <input type="submit" class="btn btn-primary" value="Confirm">

            </form:form>
        </div>
    </main>

    <jsp:include page="../common/footer.jsp"/>
</body>
</html>