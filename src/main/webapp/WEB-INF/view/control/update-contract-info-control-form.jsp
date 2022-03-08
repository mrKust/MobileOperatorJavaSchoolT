<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<title>Update client's contract</title>
<body>
<jsp:include page="../common/header.jsp"/>
<h2>Contract info</h2>
<br>
<form:form action="/common/saveContract" modelAttribute="model">

    <form:hidden path="object.id"/>
    <form:hidden path="object.contractClient.id"/>
    <form:hidden path="object.contractClient.date_of_birth"/>
    <form:hidden path="object.contractClient.passport_number"/>
    <form:hidden path="object.contractClient.address"/>
    <form:hidden path="object.contractClient.password_log_in"/>
    <form:hidden path="object.contractClient.clientNumberBlockStatus"/>
    <form:hidden path="object.contractClient.userRole"/>
    <form:hidden path="object.contractClient.roleOfUserWhoBlockedNumber"/>
    <form:hidden path="object.contractClient.contract"/>

    <br><br>
    Client's number <form:input path="object.contractClient.phone_number" readonly="true"/>
    <br><br>
    Client's name <form:input path="object.contractClient.first_name" readonly="true"/>
    <br><br>
    Client's surname <form:input path="object.contractClient.surname" readonly="true"/>
    <br><br>
    Client's email <form:input path="object.contractClient.email_address" readonly="true"/>
    <br><br>
    Client's tariff <form:select path="stringsTariff">
    <c:forEach var="tariff" items="${tariffsList}">
        <c:choose>
            <c:when test="${tariff.id} eq ${connectedTariff.id}">
                <form:option value="${tariff.id}" label="${tariff.tariff_name}" selected="true"/>
            </c:when>
            <c:otherwise>
                <form:option value="${tariff.id}" label="${tariff.tariff_name}"/>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</form:select>

    <br><br>
    <table>
        <c:forEach var="option" items="${optionsList}">
            <tr id=${option.id}><td>${option.optionsName}</td>
                <td>
                    <c:set var="contains" value="false" />
                    <c:forEach var="item" items="${connectedOptionsList}">
                        <c:if test="${item.id eq option.id}">
                            <c:set var="contains" value="true" />
                        </c:if>
                    </c:forEach>
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

    <br><br>
    <input type="submit", value="Confirm"/>
</form:form>
</body>
</html>