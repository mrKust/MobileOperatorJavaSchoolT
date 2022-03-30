<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/css/style.css">
    <title>Home</title>
</head>

<body>
<jsp:include page="header.jsp"/>
<main>
    <div class="container" id="startPage">
        <security:authorize access="isAnonymous()">
            <h2>Welcome to E-care system</h2>
            <h2>If you don't have account, you can contact support to create it</h2>
            <h2>If you already registered, login in system</h2>
            <button class="btn btn-success"
                    onclick="window.location.href='/login'">Log in</button>
        </security:authorize>
        <security:authorize access="hasRole('client')">
            <h2>Welcome back</h2>
            <h2>You role in system: client</h2>
            <h2>you features</h2>
            <button class="btn btn-success"
                    onclick="window.location.href='/client/updateClient'">My info</button>
        </security:authorize>
        <security:authorize access="hasRole('control')">
            <h2>Welcome back</h2>
            <h2>You role in system: control</h2>
            <h2>you features</h2>
            <button class="btn btn-success"
                    onclick="window.location.href='/control/allClients'">Users list</button>
        </security:authorize>
    </div>
</main>
<jsp:include page="footer.jsp"/>
</body>
</html>