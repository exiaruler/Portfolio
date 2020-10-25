<%-- 
    Document   : deleteProduct Success
    Created on : 07/06/2020, 8:37:36 PM
    Author     : Sheng
--%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Delete Success</title>
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>
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
                        <a class="nav-link disabled" href="main.jsp">Main</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="ProductListServlet">Product </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="payment.jsp">Checkout</a>
                    </li>
                </ul>
                <ul class="nav navbar-nav ml-auto">
                    <li class="nav-item ">
                        <a class="nav-link " href="logout.jsp">Logout</a>
                    </li>
                </ul>
            </div>
        </nav>
        <main class = "container-fluid main_container" style="background-color:#a5ebff">
            <div class="row d-flex p-2 min-vh-100" >
                <div class="col-md-3"></div>
                <div class="col-md-6 p-5 bg-white rounded border">
                    <h1>Product successfully deleted from database</h1>
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

