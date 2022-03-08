<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<title>Tariff full info</title>
<body>
<jsp:include page="../common/header.jsp"/>
<h2>Tariff info</h2>
<br>
<form:form action="/common/saveTariff" modelAttribute="model">

    <form:hidden path="object.id"/>

    Name <form:input path="object.tariff_name"/>
    <br><br>
    Price <form:input path="object.price"/>
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
                            <form:checkbox path="strings" value="${option.id}" name="list" checked="checked"/>
                        </c:when>
                        <c:otherwise>
                            <form:checkbox path="strings" value="${option.id}" name="list"/>
                        </c:otherwise>
                    </c:choose>
            </td>
            </tr>
        </c:forEach>
    </table>

    <br>
    This tariff <form:select path="object.availableToConnectOrNotStatus">
    <form:option value="true" label="Ready to connect"/>
    <form:option value="false" label="Couldn't be connected"/>
</form:select>
    <br><br>
    <input type="submit", value="Confirm"/>
</form:form>
</body>
</html>