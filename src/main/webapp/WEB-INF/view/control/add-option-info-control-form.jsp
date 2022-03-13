<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<title>Add new option</title>
<body>
<jsp:include page="../common/header.jsp"/>
<h2>Option info</h2>
<br>
<form:form action="/common/saveOption" modelAttribute="model">

    <form:hidden path="options.id"/>

    Name <form:input path="options.optionsName"/>
    <br><br>
    Price <form:input path="options.price"/>
    <br><br>
    Cost to add <form:input path="options.costToAdd"/>
    <br><br>
    Category of option <select name="stringOptionCategory">
    <c:forEach var="optionsCategoryItem" items="${optionsCategory}">
        <option value="${optionsCategoryItem.id}">${optionsCategoryItem.optionType}</option>
    </c:forEach>
</select>
    <br><br>
    Status <form:select path="options.availableOptionToConnectOrNot">
    <form:option value="true" label="Ready to connect"/>
    <form:option value="false" label="Couldn't be connected"/>
</form:select>
    <br><br>
    <input type="submit", value="Confirm"/>
</form:form>
</body>
</html>