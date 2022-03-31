<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/resources/css/style.css">
    <title>Contracts list</title>
</head>

<body>
    <jsp:include page="../common/header.jsp"/>

    <main>
        <div class="container">

            <h3>All Contracts</h3>
            <c:if test="${errorMessage ne null}">
                <jsp:include page="../common/error-text.jsp">
                    <jsp:param name="errorMessage" value="${errorMessage}"/>
                </jsp:include>
            </c:if>

            <form:form action="/control/allContracts" modelAttribute="model">
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
                    <option value="phoneNumber">Phone number</option>
                    <option value="priceForContractPerMonth">Price per month</option>
                    <option value="contractBlockStatus">Block status</option>
                    <option value="roleOfUserWhoBlockedContract">Blocker status</option>
                </select>
                <input type="submit" class="btn btn-success" value="Refresh">
            </div>
                <table class="table table-striped">
                    <thead>
                    <th scope="col">Client's phone number</th>
                    <th scope="col">Client's email</th>
                    <th scope="col">Current tariff</th>
                    <th scope="col">Block status</th>
                    </thead>
                    <tbody>
                    <c:forEach var="contracts" items="${allContracts}">

                        <c:url var="controlUpdateButton" value="/control/updateContract">
                            <c:param name="contractId" value="${contracts.id}"/>
                        </c:url>

                        <c:url var="deleteButton" value="/common/deleteContract">
                            <c:param name="contractId" value="${contracts.id}"/>
                        </c:url>

                        <c:url var="controlLockButton" value="/common/lockContract">
                            <c:param name="contractId" value="${contracts.id}"/>
                        </c:url>

                        <c:url var="controlUnlockButton" value="/common/unlockContract">
                            <c:param name="contractId" value="${contracts.id}"/>
                        </c:url>

                        <tr>
                            <th scope="row">${contracts.phoneNumber}</th>
                            <td>${contracts.contractClient.emailAddress}</td>
                            <td>${contracts.contractTariff.tariffName}</td>
                            <td>${contracts.contractBlockStatus}</td>
                            <td>
                                <button type="button" class="btn btn-secondary"
                                        onclick="window.location.href = '${controlUpdateButton}'">Update</button>
                                <button type="button" class="btn btn-danger"
                                        onclick="window.location.href = '${deleteButton}'">Delete</button>
                                <c:choose>
                                    <c:when test="${contracts.contractBlockStatus == false}">
                                        <button type="button" class="btn btn-warning"
                                                onclick="window.location.href = '${controlLockButton}'">Lock</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="button" class="btn btn-warning"
                                                onclick="window.location.href = '${controlUnlockButton}'">Unlock</button>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <button type="button" class="btn btn-primary"
                        onclick="window.location.href = '/control/addNewContract'">Add</button>
                <button type="button" class="btn btn-secondary"
                        onclick="window.location.href = '/control/addNewPhoneNumber'">Add new phone number</button>

                <jsp:include page="../common/pagination.jsp">
                    <jsp:param name="pageNumber" value="${pageNumber}"/>
                    <jsp:param name="action" value="/control/allContracts"/>
                </jsp:include>
            </form:form>

            </div>
    </main>

    <jsp:include page="../common/footer.jsp"/>
</body>
</html>