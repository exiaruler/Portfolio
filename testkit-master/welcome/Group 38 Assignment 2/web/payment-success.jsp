<%-- 
    Document   : payment
    Created on : 4 Jun 2020, 7:45:50 pm
    Author     : Eva
--%>
<%@page import="uts.wsd.model.Payment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment success</title>
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top">

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="index.jsp">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="register.jsp">Register</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="login.jsp">Login<span class="sr-only">(current)</span></a>
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
                </ul>
                <ul class="nav navbar-nav ml-auto">
                    <li class="nav-item ">
                        <a class="nav-link " href="logoutServlet">Logout</a>
                    </li>
                </ul>
            </div>
        </nav>
        <main class = "container-fluid main_container" style="background-color:#a5ebff">
            <div class="row d-flex p-2" style="background-color:#a5ebff">
                <div class="col-md-3"></div>
                <div class="col-md-6 p-5 bg-white rounded border mh-100">
                    <h1 class="text-center">Payment succeeded</h1>
                    <%
                        Payment payment = (Payment) session.getAttribute("payment");
                    %>
                    <!-- Add products to be paid table here -->
                    <!-- Add payment details here -->
                    <%
                        if (payment.getPaymentConfirm() == true) {
                    %>        
                    <p class="text-center">Thank you for your purchase!</p>
                    <p class="text-center">We hope you will continue shopping with us in the future.</p>
                    <div class="btn-group-horizontal text-center" role="group">
                        <button type="button" class="btn btn-primary">
                            <a href="index.jsp">Return to the home page.</a>
                        </button>
                        <button type="button" class="btn btn-primary">
                            <a href="payment.jsp">Start another payment.</a>
                        </button>
                    </div>
                    <% } else { %>
                    <p class="text-danger text-center">Either your session has expired, or it has not transferred correctly. Please return to the <a href="payment.jsp">Payment page</a> and submit again.</p>
                    <p class="text-danger text-center">We apologise for the inconvenience.</p>
                    <% }%>
                </div>
                <div class="col-md-3"   style="background-color:#a5ebff"></div>
            </div>
            <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
