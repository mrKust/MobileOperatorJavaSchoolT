<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/resources/css/style.css">
    <title>Update my info</title>
</head>

<body>

    <jsp:include page="../common/header.jsp"/>

    <c:url var="clientChangePassword" value="/client/changePasswordClient">
        <c:param name="clientId" value="${model.client.id}"/>
    </c:url>

    <c:url var="clientAddMoneyButton" value="/client/addMoneyToAccaunt">
        <c:param name="clientId" value="${model.client.id}"/>
    </c:url>

    <main>
        <div class="container">
            <h3>My info</h3>

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

            <form:form action="/common/patchClient" modelAttribute="model">

                <form:hidden path="client.id"/>
                <form:hidden path="client.userRole"/>

                <div class="input-group">
                    <span class="input-group-text">First and last name</span>
                    <form:input aria-label="First name" type="text" required="true" class="form-control" path="client.firstName" readonly="true"/>
                    <form:input aria-label="Last name" type="text" required="true" class="form-control" path="client.surname" readonly="true"/>
                </div>

                <div class="mb-3">
                    <label for="client.dateOfBirth" class="form-label">Date of birth</label>
                    <form:input id="datefield" type="date" required="true" max="" data-date-end-date="0d" class="form-control" path="client.dateOfBirth" />
                    <script>
                        var today = new Date();
                        var dd = today.getDate();
                        var mm = today.getMonth() + 1;
                        var yyyy = today.getFullYear();

                        if (dd < 10) {
                            dd = '0' + dd;
                        }

                        if (mm < 10) {
                            mm = '0' + mm;
                        }

                        today = yyyy + '-' + mm + '-' + dd;
                        document.getElementById("datefield").setAttribute("max", today)
                    </script>
                </div>

                <div class="mb-3">
                    <label for="client.passportNumber" class="form-label">Passport number</label>
                    <form:input class="form-control" required="true" type="text" path="client.passportNumber" readonly="true"/>
                </div>

                <div class="mb-3">
                    <label for="client.address" class="form-label">Home address</label>
                    <form:input class="form-control" type="text" required="true" path="client.address"/>
                </div>

                <div class="mb-3">
                    <label for="client.emailAddress" class="form-label">E-mail</label>
                    <form:input class="form-control" type="email" required="true" path="client.emailAddress" readonly="true"/>
                </div>

                <div class="mb-3">
                    <label for="client.emailAddress" class="form-label">Password</label>
                    <button type="button" class="btn btn-warning" id="changePasswordButton"
                            onclick="window.location.href= '${clientChangePassword}'">Set new password</button>
                </div>

                <label for="client.moneyBalance" class="form-label">Remaining money in the account</label>
                <div class="input-group mb-3">
                    <span class="input-group-text">$</span>
                    <form:input class="form-control" type="number" min="0" path="client.moneyBalance"
                                readonly="true"/>
                    <button type="button" class="btn btn-success" id="addMoneyButton"
                            onclick="window.location.href= '${clientAddMoneyButton}'">Add money</button>
                </div>

                <table class="table table-striped">
                    <thead>
                    <th scope="col">Phone number</th>
                    <th scope="col">Tariff</th>
                    <th scope="col">Price</th>
                    <th scope="col">Block status</th>
                    </thead>
                    <tbody>
                    <c:forEach var="contracts" items="${clientContracts}">

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

                <input type="submit" class="btn btn-primary" value="Confirm"/>

            </form:form>
        </div>
    </main>
    <jsp:include page="../common/footer.jsp"/>

</body>
</html>