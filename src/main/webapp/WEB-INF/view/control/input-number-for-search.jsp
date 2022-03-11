<!DOCTYPE html>

<head>
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
    <form action="/control/searchByPhoneNumber">
        <input type="text" name="userPhoneNumber" id="tags"/>
        <input type="submit" value="Search" placeholder="input phone number"/>
    </form>
</body>
