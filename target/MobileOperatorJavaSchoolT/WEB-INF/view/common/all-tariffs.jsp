<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="../style.css">
    <title>List of tariffs</title>
</head>

<body>
    <jsp:include page="header.jsp"/>
    <h3>All tariffs</h3>

    <div class="container">
        <table class="table table-striped">
            <thead>
                <th scope="col">Tariff's name</th>
                <th scope="col">Price</th>
                <th scope="col">Available to Connect</th>
                <th scope="col">Operations</th>
            </thead>
            <tbody>
            <c:forEach var="tariffs" items="${allTariffs}">

                <c:url var="clientUpdateButton" value="/client/updateTariff">
                    <c:param name="tariffId" value="${tariffs.id}"/>
                </c:url>

                <c:url var="controlUpdateButton" value="/control/updateTariff">
                    <c:param name="tariffId" value="${tariffs.id}"/>
                </c:url>

                <c:url var="deleteButton" value="/control/deleteTariff">
                    <c:param name="tariffId" value="${tariffs.id}"/>
                </c:url>

                <tr>
                    <th scope="row">${tariffs.tariffName}</th>
                    <td>${tariffs.price}</td>
                    <td>${tariffs.availableToConnectOrNotStatus}</td>
                    <td>
                        <security:authorize access="hasRole('control')">
                            <button type="button" class="btn btn-secondary"
                                    onclick="window.location.href = '${controlUpdateButton}'">Update</button>
                            <button type="button" class="btn btn-danger"
                                    onclick="window.location.href = '${deleteButton}'">Delete</button>
                        </security:authorize>

                        <security:authorize access="hasRole('client')">
                            <button type="button" class="btn btn-info"
                                    onclick="window.location.href = '${clientUpdateButton}'">Show external info</button>
                        </security:authorize>
                    </td>
                </tr>

            </c:forEach>
            </tbody>
        </table>
        <security:authorize access="hasRole('control')">
            <button type="button" value="Add"
                    onclick="window.location.href = '/control/addNewTariff'">Add</button>
        </security:authorize>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <jsp:include page="../common/footer.jsp"/>
</body>
</html>