<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/resources/css/style.css">
    <title>List of tariffs</title>
</head>

<body>
    <jsp:include page="header.jsp"/>

    <main>
        <div class="container">

            <h3>All tariffs</h3>

            <c:if test="${errorMessage ne null}">
                <jsp:include page="../common/error-text.jsp">
                    <jsp:param name="errorMessage" value="${errorMessage}"/>
                </jsp:include>
            </c:if>

            <form:form action="/common/allTariffs" modelAttribute="model">
                <security:authorize access="hasAnyRole('client', 'control')">
                    <input type="hidden" name="pageNumber" value="${pageNumber}">
                    <div class="input-group mb-3">
                        <select class="form-select" name="pageSize">
                            <option value="${model.pageSize}" selected>Choose page size</option>
                            <option value="5">5</option>
                            <option value="10">10</option>
                            <option value="15">15</option>
                        </select>
                        <select class="form-select" name="sortColumn">
                            <option value="${model.sortColumn}" selected>Choose column</option>
                            <option value="tariffName">Name</option>
                            <option value="price">Price</option>
                            <option value="availableOptionToConnectOrNot">Connect status</option>
                        </select>
                        <input type="submit" class="btn btn-success" value="Refresh">
                    </div>
                </security:authorize>
                <table class="table table-striped">
                    <thead>
                    <th scope="col">Tariff's name</th>
                    <th scope="col">Price</th>
                    <security:authorize access="hasRole('control')">
                        <th scope="col">Available to Connect</th>
                    </security:authorize>
                    <security:authorize access="hasAnyRole('client', 'control')">
                        <th scope="col">Operations</th>
                    </security:authorize>
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
                            <security:authorize access="hasRole('control')">
                                <td>${tariffs.availableToConnectOrNotStatus}</td>
                            </security:authorize>
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
                    <button type="button" class="btn btn-primary"
                            onclick="window.location.href = '/control/addNewTariff'">Add</button>
                </security:authorize>
                <security:authorize access="hasAnyRole('client', 'control')">
                    <jsp:include page="pagination.jsp">
                        <jsp:param name="pageNumber" value="${pageNumber}"/>
                        <jsp:param name="action" value="/common/allTarifs"/>
                    </jsp:include>
                </security:authorize>
            </form:form>
        </div>
    </main>

    <jsp:include page="../common/footer.jsp"/>
</body>
</html>