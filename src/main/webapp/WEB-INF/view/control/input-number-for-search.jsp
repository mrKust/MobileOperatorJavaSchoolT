<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../style.css">
    <title>Search</title>
</head>

<body>
    <jsp:include page="../common/header.jsp"/>

    <div class="container">
        <form action="/control/searchByPhoneNumber">
            <label class="form-label">Input number to find the user</label>
            <input class="form-control" list="numbers" name="userPhoneNumber" placeholder="input number here">
            <datalist id="numbers">
                <c:forEach var="number" items="${model.stringsNumbers}">
                    <option value="${number}">
                </c:forEach>
            </datalist>

            <input type="submit" class="btn btn-primary" value="Search">
        </form>
    </div>

    <jsp:include page="../common/footer.jsp"/>
</body>

</html>
