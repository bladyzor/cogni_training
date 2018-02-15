<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Euro Rate</title>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="js/script.js" type="text/javascript"></script>

        <link rel="stylesheet" type="text/css" href="css/stylesheet.css">
    </head>

    <body>
        <div class="column-1-2"></div>
        <div class="column-1-2"></div>

        <div class="column-1-3"></div>
        <div class="column-1-3">
            <form id="myForm" method="get" action="/euroRate">
                Date of euro rate:
                <input name="date" title="date" type="date">
                <button type="submit">Submit</button>
            </form>

            Euro rate:
            <p id="result"></p>
        </div>
        <div class="column-1-3"></div>


        <div class="column-1-2"></div>
        <div class="column-1-2"></div>
    </body>
</html>