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
    <title>Options List</title>
</head>

<body>
    <jsp:include page="header.jsp"/>

    <main>
        <div class="container">
            <h3>All Options</h3>

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

            <form:form action="/common/allOptions" modelAttribute="model">
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
                            <option value="optionsName">Name</option>
                            <option value="optionType" >Category</option>
                            <option value="price">Price</option>
                            <option value="costToAdd">Cost to add</option>
                            <option value="availableOptionToConnectOrNot">Connect status</option>
                        </select>
                        <input type="submit" class="btn btn-success" value="Refresh">
                    </div>
                </security:authorize>

            <table id="table" class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">Option's name</th>
                    <th scope="col">Category</th>
                    <th scope="col">Price</th>
                    <th scope="col">Cost to add</th>
                    <security:authorize access="hasRole('control')">
                        <th scope="col">Available to Connect</th>
                    </security:authorize>
                    <security:authorize access="hasAnyRole('client', 'control')">
                        <th scope="col">Operations</th>
                    </security:authorize>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="options" items="${allOptions}">

                    <c:url var="controlUpdateButton" value="/control/updateOption">
                        <c:param name="optionId" value="${options.id}"/>
                    </c:url>

                    <c:url var="deleteButton" value="/control/deleteOption">
                        <c:param name="optionId" value="${options.id}"/>
                    </c:url>

                    <c:url var="clientUpdateButton" value="/client/updateOption">
                        <c:param name="optionId" value="${options.id}"/>
                    </c:url>
                    <tr>
                        <th scope="row">${options.optionsName}</th>
                        <td>${options.optionType.optionType}</td>
                        <td>${options.price}</td>
                        <td>${options.costToAdd}</td>
                        <security:authorize access="hasRole('control')">
                            <td>${options.availableOptionToConnectOrNot}</td>
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
                        onclick="window.location.href = '/control/addNewOption'">Add</button>
                <button type="button" class="btn btn-secondary"
                        onclick="window.location.href = '/control/addNewOptionCategory'">Add new option category</button>
                <button type="button" class="btn btn-info"
                        onclick="window.location.href = '/control/allOptionCategories'">Show all options categories</button>
            </security:authorize>
                <security:authorize access="hasAnyRole('client', 'control')">
                    <jsp:include page="pagination.jsp">
                        <jsp:param name="pageNumber" value="${pageNumber}"/>
                        <jsp:param name="action" value="/common/allOptions"/>
                    </jsp:include>
                </security:authorize>
            </form:form>
        </div>
    </main>

    <jsp:include page="footer.jsp"/>
</body>
</html>

