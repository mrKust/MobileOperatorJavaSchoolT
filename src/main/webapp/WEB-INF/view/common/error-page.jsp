<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="style.css" type="text/css" rel="stylesheet">
    <title>Error page</title>
</head>

<body>

<jsp:include page="header.jsp"/>

<main>
    <div class="container">
        <p class="h4">Error text: ${errorMessage}</p>
    </div>

</main>
<jsp:include page="footer.jsp"/>
</body>
</html>