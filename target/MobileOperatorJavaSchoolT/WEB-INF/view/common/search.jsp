<head>
    <script src="http://code.jquery.com/jquery.min.js"></script>
    <style>
        #mycontainer, h1, h3 {
            text-align:center;
        }
        form {
            display:inline-block;
        }
        #errorMsgNumber {
            display: none;
            background: brown;
            color: white;
        }
    </style>


</head>
<body>


<div id="mycontainer">
    <form method="post" action="number" id="number">
        <div style="text-align: center;" >
            <input  width="20" type="text" data-validation="numbers" id="regNo" name="regNo" size="30" maxLength="50" placeholder="Enter Register Number">
        </div>
    </form>

</div>
<div style="text-align: center;">
    <input id="inputFields" type="button" value="Search" />
</div>

</body>

<script>

    var regNoField = document.getElementById('regNo');
    var regNoMessage = document.getElementById('regNoErrorMsgNumber');
    var inputFieldsButton = document.getElementById('inputFields');

    regNoField.addEventListener('keydown', onChange);

    function onChange(e) {
        if (e.keyCode < 48 || e.keyCode > 57) {
            regNoMessage.style.display = 'block'
        };

        if(/^\d+$/.test(regNoField.value)) {
            inputFieldsButton.disabled = false;
        } else {
            inputFieldsButton.disabled = true;
        }
    }

    $(document).ready(function(){
        $('#inputFields').click(function(event){
            if (document.getElementById('regNo').value !=""){

                $("#number").submit();

            }
        });
    });
</script>