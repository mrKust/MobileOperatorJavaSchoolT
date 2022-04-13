<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <div class="header">
        <div class="container d-flex justify-content-center py-3">
            <security:authorize access="isAnonymous()">
               <ul class="nav nav-pills">
                   <li class="nav-item">
                       <a href="/" class="nav-link" aria-current="page">Home</a>
                   </li>
                   <li class="nav-item">
                       <a href="/anonymous/allAvailableOptions" class="nav-link">Options list</a>
                   </li>
                   <li class="nav-item">
                       <a href="/anonymous/allAvailableTariffs" class="nav-link">Tariffs list</a>
                   </li>
                   <li class="nav-item">
                       <a href="/login" class="nav-link">Log in</a>
                   </li>
               </ul>
            </security:authorize>
            <security:authorize access="hasRole('client')">
               <ul class="nav nav-pills">
                   <li class="nav-item">
                       <a href="/" class="nav-link" aria-current="page">Home</a>
                   </li>
                   <li class="nav-item">
                       <a href="/common/allOptions" class="nav-link" id="clientOptionsList">Options list</a>
                   </li>
                   <li class="nav-item">
                       <a href="/common/allTariffs" class="nav-link" id="clientTariffsList">Tariffs list</a>
                   </li>
                   <li class="nav-item">
                       <a href="/client/updateClient" class="nav-link" id="clientUpdateClient">My user info</a>
                   </li>
                   <li class="nav-item">
                       <a href="/client/allContracts" class="nav-link" id="clientContractsClient">My contracts</a>
                   </li>
                   <li class="nav-item">
                       <a href="/logout" class="nav-link link-danger" id="logout">Logout</a>
                   </li>
               </ul>
            </security:authorize>
            <security:authorize access="hasRole('control')">
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
            </security:authorize>
        </div>
        <br>
        <h2>E-care project
            <small class="text-muted">you're goddamn right</small>
        </h2>
    </div>
</header>