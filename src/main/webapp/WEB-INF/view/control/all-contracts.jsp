<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="../style.css">
    <title>Contracts list</title>
</head>

<body>
    <jsp:include page="../common/header.jsp"/>
    <h3>All Contracts</h3>

    <div class="container">
        <table class="table table-striped">
            <thead>
                <th scope="col">Client's phone number</th>
                <th scope="col">Client's email</th>
                <th scope="col">Current tariff</th>
            </thead>
            <tbody>
            <c:forEach var="contracts" items="${allContracts}">

                <c:url var="controlUpdateButton" value="/control/updateContract">
                    <c:param name="contractId" value="${contracts.id}"/>
                </c:url>

                <c:url var="deleteButton" value="/control/deleteContract">
                    <c:param name="contractId" value="${contracts.id}"/>
                </c:url>

                <tr>
                    <th scope="row">${contracts.contractClient.phoneNumber}</th>
                    <td>${contracts.contractClient.emailAddress}</td>
                    <td>${contracts.contractTariff.tariffName}</td>
                    <td>
                        <button type="button" class="btn btn-secondary"
                                onclick="window.location.href = '${controlUpdateButton}'">Update</button>
                        <button type="button" class="btn btn-danger"
                                onclick="window.location.href = '${deleteButton}'">Delete</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <button type="button" class="btn btn-primary"
                onclick="window.location.href = '/control/addNewContract'">Add</button>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <jsp:include page="../common/footer.jsp"/>
</body>
</html>