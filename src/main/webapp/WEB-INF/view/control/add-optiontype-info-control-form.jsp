<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="../style.css">
    <title>Add new option type</title>
</head>

<body>
    <jsp:include page="../common/header.jsp"/>
    <h3>New option type</h3>

    <div class="container">
        <form:form action="/control/saveOptionType" modelAttribute="optionsType">

            <form:hidden path="id"/>

            <div class="mb-3">
                <label for="optionType" class="form-label">Name for category</label>
                <form:input class="form-control" path="optionType" placeholder="input name for option category here"/>
            </div>

            <input type="submit" class="btn btn-primary" value="Confirm"/>
        </form:form>

    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <jsp:include page="../common/footer.jsp"/>
</body>
</html>