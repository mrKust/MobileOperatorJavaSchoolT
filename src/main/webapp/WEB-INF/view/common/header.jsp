<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/resources/css/header.css">
</head>

<body>
<security:authorize access="isAnonymous()">
    <header>
        <div class="container">
            <ul class="nav nav-pills">
                <li class="nav-item">
                    <a href="/" class="nav-link" aria-current="page">Home</a>
                </li>
                <li class="nav-item">
                    <a href="/common/allOptions" class="nav-link">Options list</a>
                </li>
                <li class="nav-item">
                    <a href="/common/allTariffs" class="nav-link">Tariffs list</a>
                </li>
                <li class="nav-item">
                    <a href="/login" class="nav-link">Log in</a>
                </li>
            </ul>
        </div>
        <br>
        <h2>E-care project
            <small class="text-muted">you're goddamn right</small>
        </h2>
    </header>
</security:authorize>


<security:authorize access="hasRole('client')">
    <header>
        <div class="container">
            <ul class="nav nav-pills">
                <li class="nav-item">
                    <a href="/" class="nav-link" aria-current="page">Home</a>
                </li>
                <li class="nav-item">
                    <a href="/common/allOptions" class="nav-link">Options list</a>
                </li>
                <li class="nav-item">
                    <a href="/common/allTariffs" class="nav-link">Tariffs list</a>
                </li>
                <li class="nav-item">
                    <a href="/client/updateClient" class="nav-link">My user info</a>
                </li>
                <li class="nav-item">
                    <a href="/client/updateContract" class="nav-link">My contract</a>
                </li>
                <li class="nav-item">
                    <a href="/logout" class="nav-link link-danger">Logout</a>
                </li>
            </ul>
        </div>
        <br>
        <h2>E-care project
            <small class="text-muted">you're goddamn right</small>
        </h2>
    </header>
</security:authorize>

<security:authorize access="hasRole('control')">
    <header>
        <div class="container d-flex justify-content-center py-3">
            <ul id="menu" class="nav nav-pills">
                <li>
                    <a href="/" class="nav-link" aria-current="page">Home</a>
                </li>
                <li>
                    <a href="/common/allOptions" class="nav-link">Options list</a>
                </li>
                <li>
                    <a href="/common/allTariffs" class="nav-link">Tariffs list</a>
                </li>
                <li>
                    <a href="/control/allClients" class="nav-link">Clients list</a>
                </li>
                <li>
                    <a href="/control/allContracts" class="nav-link">Contracts list</a>
                </li>
                <li>
                    <a href="/control/inputNumberToSearch" class="nav-link">Search</a>
                </li>
                <li>
                    <a href="/logout" class="nav-link link-danger">Logout</a>
                </li>
            </ul>
        </div>
        <br>
        <h2>E-care project
            <small class="text-muted">you're goddamn right</small>
        </h2>
    </header>
</security:authorize>
</body>
</html>