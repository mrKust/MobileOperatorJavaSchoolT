<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

<html>
<title>My contract</title>
<body>
<jsp:include page="../common/header.jsp"/>
<h2>Contract info</h2>
<br>
<form:form action="/common/saveContract" modelAttribute="model">

    <form:hidden path="contract.id"/>
    <form:hidden path="contract.contractClient.id"/>
    <form:hidden path="contract.contractClient.dateOfBirth"/>
    <form:hidden path="contract.contractClient.passportNumber"/>
    <form:hidden path="contract.contractClient.address"/>
    <form:hidden path="contract.contractClient.passwordLogIn"/>
    <form:hidden path="contract.contractClient.userRole"/>
    <form:hidden path="contract.contractClient.roleOfUserWhoBlockedNumber"/>
    <form:hidden path="contract.contractClient.contract"/>
    <form:hidden path="contract.phoneNumber"/>
    <form:hidden path="connectedOptions"/>
    <form:hidden path="operationType"/>
    <form:hidden path="contract.contractClient.phoneNumber"/>
    <form:hidden path="contract.contractClient.firstName"/>
    <form:hidden path="contract.contractClient.surname"/>
    <form:hidden path="contract.contractClient.emailAddress"/>

    <br><br>
    Client's current tariff <form:input path="contract.contractTariff.tariffName" readonly="true"/>
    <c:if test="${model.contract.contractClient.clientNumberReadyToWorkStatus eq true}">
        Switch to tariff <select name="stringsTariff">
        <c:forEach var="tariff" items="${tariffsList}">
            <c:choose>
                <c:when test="${tariff.id eq connectedTariff.id}">
                    <option value="${tariff.id}" selected="selected">${tariff.tariffName}</option>
                </c:when>
                <c:otherwise>
                    <c:if test="${tariff.availableToConnectOrNotStatus eq true}">
                        <option value="${tariff.id}">${tariff.tariffName}</option>
                    </c:if>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
    </c:if>

    <c:choose>
        <c:when test="${model.contract.contractClient.clientNumberReadyToWorkStatus eq true}">
            <br><br>
            <table>
                <c:forEach var="option" items="${availableForTariffOptionsList}">
                    <tr id=${option.id}><td>Category: ${option.optionType.optionType} Name: ${option.optionsName}</td>
                        <td>
                            <c:set var="contains" value="false" />
                            <c:if test="${fn:length(connectedOptionsList)>0}">
                                <c:forEach var="item" items="${connectedOptionsList}">
                                    <c:if test="${item.id eq option.id}">
                                        <c:set var="contains" value="true" />
                                    </c:if>
                                </c:forEach>
                            </c:if>
                            <c:choose>
                                <c:when test="${contains==true}">
                                    <form:checkbox path="stringsOptions" value="${option.id}" name="list" checked="checked"/>
                                </c:when>
                                <c:otherwise>
                                    <form:checkbox path="stringsOptions" value="${option.id}" name="list"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <br><br>
            Connected options
            <table>
                <c:forEach var="item" items="${connectedOptionsList}">
                    <tr id=${item.id}><td>${item.optionsName}</td></tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
    <br><br>

    <br><br>
    <c:if test="${model.contract.contractClient.clientNumberReadyToWorkStatus eq true}">
        <input type="submit", value="Confirm"/>
    </c:if>
</form:form>
<c:if test="${model.contract.contractClient.clientNumberReadyToWorkStatus ne true}">
    <input type="button" value="Confirm"
           onclick="window.location.href = '/'"/>
</c:if>
</body>
</html>