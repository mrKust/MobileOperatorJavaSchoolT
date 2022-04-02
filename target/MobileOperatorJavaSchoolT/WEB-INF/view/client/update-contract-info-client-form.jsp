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
    <title>My contract</title>
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
                <jsp:include page="../common/success-text.jsp">
                    <jsp:param name="successMessage" value="${successMessage}"/>
                </jsp:include>
            </c:if>

            <c:if test="${model.contract.roleOfUserWhoBlockedContract eq 'control'}">
                <div class="alert alert-warning" role="alert">
                    This contact was blocked by administration. You can't change anything. Contact to help if you need.
                </div>
            </c:if>

            <form:form action="/common/patchContract" modelAttribute="model">

                <form:hidden path="contract.id"/>
                <form:hidden path="contract.contractClient.id"/>
                <form:hidden path="contract.contractClient.dateOfBirth"/>
                <form:hidden path="contract.contractClient.passportNumber"/>
                <form:hidden path="contract.contractClient.address"/>
                <form:hidden path="contract.contractClient.userRole"/>
                <form:hidden path="contract.roleOfUserWhoBlockedContract"/>
                <form:hidden path="contract.phoneNumber"/>
                <form:hidden path="connectedOptions"/>
                <form:hidden path="contract.contractClient.firstName"/>
                <form:hidden path="contract.contractClient.surname"/>
                <form:hidden path="contract.contractClient.emailAddress"/>

                <div class="mb-3">
                    <label for="contract.contractClient.moneyBalance" class="form-label">Money on balance</label>
                    <form:input class="form-control" path="contract.contractClient.moneyBalance"
                                 readonly="true"/>
                </div>

                <div class="input-group">
                    <span class="input-group-text">Current tariff and Available to switch tariff</span>
                    <form:input class="form-control" type="text" path="contract.contractTariff.tariffName" readonly="true"/>
                    <c:if test="${model.contract.contractBlockStatus eq false}">
                        <select class="form-select" name="stringsTariff">
                            <c:forEach var="tariff" items="${tariffsList}">
                                <c:choose>
                                    <c:when test="${tariff.id eq connectedTariff.id}">
                                        <option value="${tariff.id}" selected>${tariff.tariffName} ${tariff.price}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${tariff.availableToConnectOrNotStatus eq true}">
                                            <option value="${tariff.id}">${tariff.tariffName} ${tariff.price}</option>
                                        </c:if>
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
                            <c:if test="${fn:length({model.contract.connectedOptions})>0}">
                                <c:forEach var="item" items="${model.contract.connectedOptions}">
                                    <c:if test="${item.id eq option.id}">
                                        <c:set var="contains" value="true" />
                                    </c:if>
                                </c:forEach>
                            </c:if>
                            <c:choose>
                                <c:when test="${contains==true}">
                                    <div class="mb-3">
                                        <label class="form-check-label">${option.optionType.optionType} ${option.optionsName}  ${option.costToAdd} ${option.price}</label>
                                        <form:checkbox class="form-check-input" path="stringsOptions" value="${option.id}" name="list" checked="checked"/>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="mb-3">
                                        <label class="form-check-label">${option.optionType.optionType} ${option.optionsName}  ${option.costToAdd} ${option.price}</label>
                                        <form:checkbox class="form-check-input" path="stringsOptions" value="${option.id}" name="list"/>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="mb-3">
                            <label class="form-check-label">Connected options (Option category, name, cost to connect, monthly price)</label>
                        </div>
                        <c:forEach var="option" items="${model.contract.connectedOptions}">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="connectedOption" disabled>
                                <label class="form-check-label" for="connectedOption">
                                        ${option.optionType.optionType} ${option.optionsName} ${option.costToAdd} ${option.price}
                                </label>
                                <form:checkbox class="form-check-input" path="stringsOptions" value="${option.id}" name="list" checked="checked" disabled="true"/>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>

                <c:if test="${model.contract.contractBlockStatus eq false}">
                    <input type="submit" class="btn btn-primary" value="Confirm"/>
                </c:if>

                <c:if test="${model.contract.contractBlockStatus eq true}">
                    <button type="button" class="btn btn-primary"
                            onclick="window.location.href = '/client/allContracts'">Confirm</button>
                </c:if>
            </form:form>
        </div>
    </main>

    <jsp:include page="../common/footer.jsp"/>
</body>
</html>