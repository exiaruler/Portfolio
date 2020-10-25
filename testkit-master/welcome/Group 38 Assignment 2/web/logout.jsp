<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout Page</title>
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top">
            <span class="navbar-brand mb-0 h1">IoTBay</span>
        </nav>
        <main class = "container-fluid main_container" style="background-color:#a5ebff">
            <div class="row d-flex p-2" >
                <div class="col-md-3"></div>
                <div class="col-md-6 p-5 bg-white rounded border">
                    <h1>Logout</h1>
                    <br>
                    <p>You have now logged out</p>
                    <a href="index.jsp" class="btn btn-primary btn-md">Return to home page</a>
                </div>
            </div>
            <div class="col-md-3"></div>
        </main>

        <footer class="card-footer">
            <div class="container">
                <span class="text-muted">
                    IoTBay 2020
                </span>
            </div>
        </footer>
    </body>
</html>
