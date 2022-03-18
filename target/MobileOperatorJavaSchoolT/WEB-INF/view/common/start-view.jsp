<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="style.css" type="text/css" rel="stylesheet">
    <title>Home</title>
</head>

<body>

<jsp:include page="header.jsp"/>

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
<jsp:include page="footer.jsp"/>
</body>
</html>