<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="../style.css">
    <title>Add new contract</title>
</head>

<body>
    <jsp:include page="../common/header.jsp"/>
    <h3>Contract info</h3>

    <div class="container">
        <form:form action="/common/saveContract" modelAttribute="model">

            <form:hidden path="contract.id"/>
            <form:hidden path="operationType"/>

            <div class="mb-3">
                <label class="form-label">Select client</label>
                <select class="form-select" name="stringsClients">
                    <c:forEach var="client" items="${clientsList}">
                        <c:if test="${client.contract eq null}">
                            <option value="${client.id}">${client.phoneNumber} ${client.surname} ${client.emailAddress}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>

            <div class="mb-3">
                <label class="form-label">Select tariff</label>
                <select class="form-select" name="stringsTariff">
                    <c:forEach var="tariff" items="${tariffsList}">
                        <c:if test="${tariff.availableToConnectOrNotStatus eq true}">
                            <option value="${tariff.id}">${tariff.tariffName}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>

            <input type="submit" class="btn btn-primary" value="Confirm">

        </form:form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <jsp:include page="../common/footer.jsp"/>
</body>
</html>