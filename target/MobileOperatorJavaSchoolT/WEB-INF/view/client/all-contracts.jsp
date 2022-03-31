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

        <h3>All my contracts</h3>
        <c:if test="${errorMessage ne null}">
            <jsp:include page="../common/error-text.jsp">
                <jsp:param name="errorMessage" value="${errorMessage}"/>
            </jsp:include>
        </c:if>

        <c:choose>
            <c:when test="">
                <h4>You don't have any contracts yet</h4>
            </c:when>
            <c:otherwise>
                <form:form action="/client/allContracts" modelAttribute="model">
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
                        <th scope="col">Current tariff</th>
                        <th scope="col">Price per month</th>
                        <th scope="col">Lock status</th>
                        </thead>
                        <tbody>
                        <c:forEach var="contracts" items="${allContracts}">

                            <c:url var="controlUpdateButton" value="/client/updateContract">
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
                                <td>${contracts.contractTariff.tariffName}</td>
                                <td>${contracts.priceForContractPerMonth}</td>
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
                                            <c:if test="${contracts.roleOfUserWhoBlockedContract ne 'control'}">
                                                <button type="button" class="btn btn-warning"
                                                        onclick="window.location.href = '${controlUnlockButton}'">Unlock</button>
                                            </c:if>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </form:form>
            </c:otherwise>
        </c:choose>
        <button type="button" class="btn btn-primary"
                onclick="window.location.href = '/control/addNewContract'">Add</button>
        <br><br>
        <nav aria-label="Page navigation">
            <ul class="pagination" id="listing">
                <c:if test="${(pageNumber ne 1) && (pageNumber ne null)}">
                    <c:url var="changePagePrevious" value="/client/allContracts">
                        <c:param name="pageNumber" value="${pageNumber-1}"/>
                    </c:url>
                    <li class="page-item"><a class="page-link" href="${changePagePrevious}">Previous</a></li>
                </c:if>
                <c:forEach begin="1" end="${numberOfPages}" step="1" var="index">

                    <c:url var="changePageNext" value="/client/allContracts">
                        <c:choose>
                            <c:when test="${pageNumber == null}">
                                <c:param name="pageNumber" value="${2}"/>
                            </c:when>
                            <c:otherwise>
                                <c:param name="pageNumber" value="${pageNumber+1}"/>
                            </c:otherwise>
                        </c:choose>
                    </c:url>

                    <c:url var="changePage" value="/client/allContracts">
                        <c:param name="pageNumber" value="${index}"/>
                    </c:url>

                    <li class="page-item"><a class="page-link" href="${changePage}">${index}</a></li>
                </c:forEach>
                <c:if test="${(pageNumber < numberOfPages) || ( (1 != numberOfPages) && (pageNumber == null))}">
                    <li class="page-item"><a class="page-link" href="${changePageNext}">Next</a></li>
                </c:if>
            </ul>
        </nav>
    </div>
</main>

<jsp:include page="../common/footer.jsp"/>
</body>
</html>