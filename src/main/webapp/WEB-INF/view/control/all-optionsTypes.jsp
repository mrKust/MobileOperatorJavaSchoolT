<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/resources/css/style.css">
    <title>Options type list</title>
</head>

<body>
    <jsp:include page="../common/header.jsp"/>
    <h3>All options types</h3>

    <div class="container">

        <jsp:include page="../common/error-text.jsp">
            <jsp:param name="errorMessage" value="${errorMessage}"/>
        </jsp:include>

        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Type</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="optionsType" items="${allOptionsType}">

                <c:url var="deleteButton" value="/control/deleteOptionType">
                    <c:param name="optionsTypeId" value="${optionsType.id}"/>
                </c:url>

                <tr>
                    <th scope="row">${optionsType.optionType}</th>
                    <td>
                        <button type="button" class="btn btn-danger"
                            onclick="window.location.href = '${deleteButton}'">Delete</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <br>
        <button type="button" class="btn btn-primary"
                onclick="window.location.href = '/control/addNewOptionCategory'">Add</button>
    </div>

    <jsp:include page="../common/footer.jsp"/>
</body>
</html>
