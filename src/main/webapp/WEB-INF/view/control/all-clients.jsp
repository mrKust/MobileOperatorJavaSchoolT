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
    <title>Clients list</title>
</head>

<body>
    <jsp:include page="../common/header.jsp"/>

    <main>
        <div class="container">

            <h3>All Clients</h3>
            <c:if test="${errorMessage ne null}">
                <jsp:include page="../common/error-text.jsp">
                    <jsp:param name="errorMessage" value="${errorMessage}"/>
                </jsp:include>
            </c:if>

            <form:form action="/control/allClients" modelAttribute="model">
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
                    <option value="firstName">Name</option>
                    <option value="surname">Surname</option>
                    <option value="dateOfBirth">Date of birth</option>
                    <option value="emailAddress">email</option>
                    <option value="userRole">Role</option>
                </select>
                <input type="submit" class="btn btn-success" value="Refresh">
            </div>
                <table class="table table-striped">
                    <thead>
                    <th scope="col">Client's name</th>
                    <th scope="col">Client's surname</th>
                    <th scope="col">Date of birth</th>
                    <th scope="col">email</th>
                    <th scope="col">User's role</th>
                    <th scope="col">Operations</th>
                    </thead>
                    <tbody>
                    <c:forEach var="clients" items="${allClients}">

                        <c:url var="controlUpdateButton" value="/control/updateClient">
                            <c:param name="clientId" value="${clients.id}"/>
                        </c:url>

                        <c:url var="deleteButton" value="/control/deleteClient">
                            <c:param name="clientId" value="${clients.id}"/>
                        </c:url>

                        <tr>
                            <th scope="row">${clients.surname}</th>
                            <td>${clients.firstName}</td>
                            <td>${clients.dateOfBirth}</td>
                            <td>${clients.emailAddress}</td>
                            <td>${clients.userRole}</td>
                            <td>
                                <security:authorize access="hasRole('control')">
                                    <button type="button" class="btn btn-secondary"
                                            onclick="window.location.href = '${controlUpdateButton}'">Update</button>
                                    <button type="button" class="btn btn-danger"
                                            onclick="window.location.href = '${deleteButton}'">Delete</button>
                                </security:authorize>
                            </td>
                        </tr>

                    </c:forEach>
                    </tbody>
                </table>
                <button type="button" class="btn btn-primary"
                        onclick="window.location.href = '/control/addNewClient'">Add</button>
                <br><br>
                <nav aria-label="Page navigation">
                    <ul class="pagination" id="listing">
                        <c:if test="${(pageNumber ne 1) && (pageNumber ne null)}">
                            <c:url var="changePagePrevious" value="/control/allClients">
                                <c:param name="pageNumber" value="${pageNumber-1}"/>
                            </c:url>
                            <li class="page-item"><a class="page-link" href="${changePagePrevious}">Previous</a></li>
                        </c:if>
                        <c:forEach begin="1" end="${numberOfPages}" step="1" var="index">

                            <c:url var="changePageNext" value="/control/allClients">
                                <c:choose>
                                    <c:when test="${pageNumber == null}">
                                        <c:param name="pageNumber" value="${2}"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:param name="pageNumber" value="${pageNumber+1}"/>
                                    </c:otherwise>
                                </c:choose>
                            </c:url>

                            <c:url var="changePage" value="/control/allClients">
                                <c:param name="pageNumber" value="${index}"/>
                            </c:url>

                            <li class="page-item"><a class="page-link" href="${changePage}">${index}</a></li>
                        </c:forEach>
                        <c:if test="${(pageNumber < numberOfPages) || ( (1 != numberOfPages) && (pageNumber == null))}">
                            <li class="page-item"><a class="page-link" href="${changePageNext}">Next</a></li>
                        </c:if>
                    </ul>
                </nav>
            </form:form>
        </div>
    </main>

    <jsp:include page="../common/footer.jsp"/>
</body>
</html>