<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/resources/css/style.css">
    <title>Update client's contract</title>
</head>

<body>
    <jsp:include page="../common/header.jsp"/>

    <main>
        <div class="container">

            <h3>Contract info</h3>
            <c:if test="${errorMessage ne null}">
                <jsp:include page="../common/error-text.jsp">
                    <jsp:param name="errorMessage" value="${errorMessage}"/>
                </jsp:include>
            </c:if>

            <c:if test="${successMessage ne null}">
                <div class="alert alert-success" role="alert">
                        ${successMessage}
                </div>
            </c:if>

            <form:form action="/common/patchContract" modelAttribute="model">

                <form:hidden path="contract.id"/>
                <form:hidden path="contract.contractClient.id"/>
                <form:hidden path="contract.contractClient.dateOfBirth"/>
                <form:hidden path="contract.contractClient.passportNumber"/>
                <form:hidden path="contract.contractClient.address"/>
                <form:hidden path="contract.contractClient.userRole"/>
                <form:hidden path="connectedOptions"/>

                <div class="mb-3">
                    <label for="contract.phoneNumber" class="form-label">Client's phone</label>
                    <form:input class="form-control" path="contract.phoneNumber"
                                type="tel" readonly="true"/>
                </div>

                <div class="input-group">
                    <span class="input-group-text">First and last name</span>
                    <form:input aria-label="First name" type="text" class="form-control" path="contract.contractClient.firstName" readonly="true"/>
                    <form:input aria-label="Last name" type="text" class="form-control" path="contract.contractClient.surname" readonly="true"/>
                </div>

                <div class="mb-3">
                    <label for="contract.contractClient.emailAddress" class="form-label">E-mail address</label>
                    <form:input class="form-control" type="email" path="contract.contractClient.emailAddress" readonly="true"/>
                </div>

                <div class="mb-3">
                    <label for="contract.contractClient.moneyBalance" class="form-label">Money on balance</label>
                    <form:input class="form-control" path="contract.contractClient.moneyBalance"
                                readonly="true"/>
                </div>

                <div class="mb-3">
                    <label for="contract.contractTariff.tariffName" class="form-label">Client's current tariff</label>
                    <form:input class="form-control" type="text" path="contract.contractTariff.tariffName" readonly="true"/>
                </div>

                <div class="mb-3">
                    <c:if test="${model.contract.contractBlockStatus eq false}">
                        <label class="form-label">Switch to tariff</label>
                        <select class="form-select" name="stringsTariff">
                            <c:forEach var="tariff" items="${tariffsList}">
                                <c:choose>
                                    <c:when test="${tariff.id eq connectedTariff.id}">
                                        <option value="${tariff.id}" selected>${tariff.tariffName} ${tariff.price}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${tariff.id}">${tariff.tariffName} ${tariff.price}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </c:if>
                </div>

                <c:choose>
                    <c:when test="${model.contract.contractBlockStatus eq false}">
                        <div class="mb-3">
                            <label class="form-check-label">Available options (Option category, name, cost to connect, monthly price)</label>
                        </div>
                        <c:forEach var="option" items="${availableForTariffOptionsList}">
                            <c:set var="contains" value="false" />
                            <c:if test="${fn:length(model.contract.connectedOptions)>0}">
                                <c:forEach var="item" items="${model.contract.connectedOptions}">
                                    <c:if test="${item.id eq option.id}">
                                        <c:set var="contains" value="true" />
                                    </c:if>
                                </c:forEach>
                            </c:if>
                            <c:choose>
                                <c:when test="${contains==true}">
                                    <div class="mb-3">
                                        <label class="form-check-label">${option.optionType.optionType} ${option.optionsName} ${option.costToAdd} ${option.price}</label>
                                        <form:checkbox class="form-check-input" path="stringsOptions" value="${option.id}" name="list" checked="checked"/>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${option.availableOptionToConnectOrNot == true}">
                                        <div class="mb-3">
                                            <label class="form-check-label">${option.optionType.optionType} ${option.optionsName} ${option.costToAdd} ${option.price}</label>
                                            <form:checkbox class="form-check-input" path="stringsOptions" value="${option.id}" name="list"/>
                                        </div>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="mb-3">
                            <label class="form-check-label">Connected options</label>
                        </div>
                        <c:forEach var="option" items="${model.contract.connectedOptions}">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="connectedOption" disabled>
                                <label class="form-check-label" for="connectedOption">
                                        ${option.optionType.optionType} ${option.optionsName}
                                </label>
                                <form:checkbox class="form-check-input" path="stringsOptions" value="${option.id}" name="list" checked="checked" disabled="true"/>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>


                <c:choose>
                    <c:when test="${model.contract.contractBlockStatus eq false}">
                        <input type="submit" class="btn btn-primary" value="Confirm"/>
                    </c:when>
                    <c:otherwise>
                        <button type="button" class="btn btn-primary"
                                onclick="window.location.href = '/control/allContracts'">Confirm</button>
                    </c:otherwise>
                </c:choose>
            </form:form>
        </div>
    </main>

    <jsp:include page="../common/footer.jsp"/>
</body>
</html>