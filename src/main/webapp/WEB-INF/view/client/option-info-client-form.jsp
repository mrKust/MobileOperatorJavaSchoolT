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
    <title>Option info</title>
</head>

<body>
    <jsp:include page="../common/header.jsp"/>
    <h3>Option info</h3>

    <div class="container">

        <jsp:include page="../common/error-text.jsp">
            <jsp:param name="errorMessage" value="${errorMessage}"/>
        </jsp:include>

        <form:form action="/common/saveOption" modelAttribute="model">

            <form:hidden path="options.id"/>
            <form:hidden path="options.optionType.id"/>

            <div class="mb-3">
                <label for="options.optionsName" class="form-label">Name</label>
                <form:input class="form-control" type="text" path="options.optionsName" readonly="true"/>
            </div>

            <div class="mb-3">
                <label for="options.price" class="form-label">Price</label>
                <form:input class="form-control" type="number" min="0" path="options.price"
                            readonly="true"/>
            </div>

            <div class="mb-3">
                <label for="options.costToAdd" class="form-label">Cost to connect</label>
                <form:input class="form-control" type="number" min="0" path="options.costToAdd"
                            readonly="true"/>
            </div>

            <div class="mb-3">
                <label for="options.optionType.optionType" class="form-label">Price</label>
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

    <jsp:include page="../common/footer.jsp"/>
</body>
</html>