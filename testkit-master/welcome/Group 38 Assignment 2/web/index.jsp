<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>Home Page</title>
    </head>
    <body class="text-center ">

        <nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top">
            <span class="navbar-brand mb-0 h1">IoTBay</span>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="register.jsp">Register</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="login.jsp">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="main.jsp">Main</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="ProductListServlet">Product </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="payment.jsp">Checkout</a>
                    </li>
                     <li class="nav-item">
                        <a class="nav-link" href="customerListServlet">Manage Customers</a>
                    </li>
                </ul>
            </div>
        </nav>


        <main class = "container-fluid main_container min-vh-100 " style="background-color:#a5ebff">
            <div class="row d-flex p-2 text-center w-100 h-100" >
                <div class="col-md-3"></div>
                <div class="col-md-6 min-vh-100 rounded border border-primary bg-white">
                    <br>
                    <h1>Welcome to the IoTBay Store</h1>
                    <p>Please log in or register to continue.</p>
                    <div class=" btn-group-justified">
                        <a href="./register.jsp" class="btn btn-primary btn-lg" role="button" aria-pressed="true">Register</a>
                        <a href="./login.jsp" class="btn btn-success btn-lg" role="button" aria-pressed="true">Log in</a>
                        <a href="ProductListServlet" class="btn btn-info btn-lg" role="button" aria-pressed="true">View products</a>
                    </div>
                    <div>
                        <br />
                        <br />
                    </div>
                    <div class="col-md-3"></div>
                </div>
            </div>
        </main>

        <footer class="card-footer">
            <div class="container">
                <span class="text-muted">
                    IoTBay 2020
                </span>
            </div>
        </footer>
        <script type="text/javascript" src="./js/jquery-3.4.1.min.js"></script>
        <script type="text/javascript" src="./js/bootstrap.min.js"></script>
        <script type="text/javascript" src="./js/bootstrap.bundle.min.js"></script>
        <jsp:include page="/ConnServlet" flush="true"/>
    </body>

</html>
