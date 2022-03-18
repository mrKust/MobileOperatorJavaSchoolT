<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../css/style.css">
    <title>Clients list</title>
</head>

<body>
    <jsp:include page="../common/header.jsp"/>
    <h3>All Clients</h3>

    <div class="container">
        <table class="table table-striped">
            <thead>
                <th scope="col">Client's name</th>
                <th scope="col">Client's surname</th>
                <th scope="col">Mobile number</th>
                <th scope="col">email</th>
                <th scope="col">User's role</th>
                <th scope="col">Ready to work status</th>
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

                <c:url var="controlLockButton" value="/common/lockClient">
                    <c:param name="clientId" value="${clients.id}"/>
                </c:url>

                <c:url var="controlUnlockButton" value="/common/unlockClient">
                    <c:param name="clientId" value="${clients.id}"/>
                </c:url>

                <tr>
                    <th scope="row">${clients.surname}</th>
                    <td>${clients.firstName}</td>
                    <td>${clients.phoneNumber}</td>
                    <td>${clients.emailAddress}</td>
                    <td>${clients.userRole}</td>
                    <td>${clients.clientNumberReadyToWorkStatus}</td>
                    <td>
                        <security:authorize access="hasRole('control')">
                            <button type="button" class="btn btn-secondary"
                                    onclick="window.location.href = '${controlUpdateButton}'">Update</button>
                            <button type="button" class="btn btn-danger"
                                    onclick="window.location.href = '${deleteButton}'">Delete</button>
                            <c:choose>
                                <c:when test="${clients.clientNumberReadyToWorkStatus==true}">
                                    <button type="button" class="btn btn-warning"
                                            onclick="window.location.href = '${controlLockButton}'">Lock</button>
                                </c:when>
                                <c:otherwise>
                                    <button type="button" class="btn btn-warning"
                                            onclick="window.location.href = '${controlUnlockButton}'">Unlock</button>
                                </c:otherwise>
                            </c:choose>
                        </security:authorize>
                    </td>
                </tr>

            </c:forEach>
            </tbody>
        </table>
        <button type="button" class="btn btn-primary"
                onclick="window.location.href = '/control/addNewClient'">Add</button>
    </div>

    <jsp:include page="../common/footer.jsp"/>
</body>
</html>