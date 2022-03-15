<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="../style.css">
    </head>

    <body>
        <header>
            <security:authorize access="hasRole('client')">
                <div class="container">
                    <div class="row">
                        <div class="col-2">
                            <a href="/">Main page</a>
                        </div>
                        <div class="col-2">
                            <a href="/common/allOptions" class="link-info">Options list</a>
                        </div>
                        <div class="col-2">
                            <a href="/common/allTariffs" class="link-info">Tariffs list</a>
                        </div>
                        <div class="col-2">
                            <a href="/client/updateClient" class="link-primary">My user info</a>
                        </div>
                        <div class="col-2">
                            <a href="/client/updateContract" class="link-primary">My contract</a>
                        </div>
                        <div class="col-2">
                            <a href="/logout" class="link-danger">Logout</a></nav>
                        </div>
                    </div>
                </div>
            </security:authorize>

            <security:authorize access="hasRole('control')">
                <div class="container">
                    <div class="row">
                        <div class="col-2">
                            <a href="/">Main page</a>
                        </div>
                        <div class="col-2">
                            <a href="/common/allOptions" class="link-info">Options list</a>
                        </div>
                        <div class="col-2">
                            <a href="/common/allTariffs" class="link-info">Tariffs list</a>
                        </div>
                        <div class="col-2">
                            <a href="/control/allClients" class="link-primary">Clients list</a>
                        </div>
                        <div class="col-2">
                             <a href="/control/allContracts" class="link-primary">Contracts list</a>
                        </div>
                        <div class="col-1">
                            <a href="/control/inputNumberToSearch" class="link-info">Search</a>
                        </div>
                        <div class="col-1">
                            <a href="/logout" class="link-danger">Logout</a></nav>
                        </div>
                    </div>
                </div>

            </security:authorize>

        <h2>E-care project
            <small class="text-muted">you're goddamn right</small>
        </h2>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</header>
</body>

</html>