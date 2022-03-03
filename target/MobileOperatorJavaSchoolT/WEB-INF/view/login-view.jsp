<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>
        Log in to eCare
    </title>
</head>
<body>

<header>
    <h1>Corgi the best</h1>
    <a href="/allOptions">
        Options list
    </a>
    <br>
    <a href="/allTariffs">
        Tariffs list
    </a>
    <security:authorize access="hasRole('client')">
        <br>
        <a href="/ownClientInfo">
            My user info
        </a>
    <br>
    <a href="/ownContractInfo">
        My contract
    </a>
    </security:authorize>

    <security:authorize access="hasRole('control')">
        <br>
        <a href="/allClients">
            Clients list
        </a>
        <br>
        <a href="/allContracts">
            Contracts list
        </a>
    </security:authorize>
</header>

<main>
    <section>
        <h2>
            Picture 1
        </h2>
        <img alt="Corgi1" src="https://mymodernmet.com/wp/wp-content/uploads/2020/10/cooper-baby-corgi-dogs-8.jpg"
             width="70" height="100">
    </section>

    <section>
        <h2>
            Picture 1
        </h2>
        <img alt="Corgi2" src="https://68.img.avito.st/640x480/11167971868.jpg"
             width="100" height="70">
    </section>

</main>

<footer>
    <a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ">
        Rick Astley
    </a>

</footer>
</body>
</html>