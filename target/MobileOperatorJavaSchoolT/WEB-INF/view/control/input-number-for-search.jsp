<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="../style.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
    <script>
        $( function() {
            var availableTags = ${model.stringsNumbers}
                $( "#tags" ).autocomplete({
                    source: availableTags
                });
        } );
    </script>
    <title>Search</title>
</head>

<body>
    <jsp:include page="../common/header.jsp"/>

    <div class="container">
        <form action="/control/searchByPhoneNumber">

            <input class="form-control" type="text" name="userPhoneNumber" id="tags"/>
            
            <input type="submit" class="btn btn-primary" value="Search" placeholder="input phone number"/>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <jsp:include page="../common/footer.jsp"/>
</body>

</html>

<%--<head>
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
    <script>
        $( function() {
            var availableTags = ${model.stringsNumbers}
            $( "#tags" ).autocomplete({
                source: availableTags
            });
        } );
    </script>
</head>
<body>
    <jsp:include page="../common/header.jsp"/>

    <form action="/control/searchByPhoneNumber">
        <input type="text" name="userPhoneNumber" id="tags"/>
        <input type="submit" value="Search" placeholder="input phone number"/>
    </form>

    <jsp:include page="../common/footer.jsp"/>
</body>--%>
