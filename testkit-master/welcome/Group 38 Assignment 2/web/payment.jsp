<%-- 
    Document   : payment
    Created on : 4 Jun 2020, 7:45:50 pm
    Author     : Eva
--%>
<%@page import="uts.wsd.model.Payment"%>
<%@page import="uts.wsd.model.CreditCard"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment</title>
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
                    <h1 class="text-center">Payment</h1>
                    <p class="text-center">Please select a payment method below.</p>
                    <% //Add session beans here %>
                    <%
                        String emptyErr = (String) session.getAttribute("ccEmptyErr");
                        String nameErr = (String) session.getAttribute("ccNameErr");
                        String numberErr = (String) session.getAttribute("ccNumberErr");
                        String cvvErr = (String) session.getAttribute("ccCvvErr");
                        String dateErr = (String) session.getAttribute("ccDateErr");
                        String managerErr = (String) session.getAttribute("managerErr");
                    %>
                    <!-- Add products to be paid table here -->
                    <div class="btn-group-horizontal text-center" role="group">
                        <button type="button" class="btn btn-warning" data-toggle="collapse" data-target="#creditcard">
                            Pay with credit card
                        </button>
                        <button type="button" class="btn btn-primary disabled" data-toggle="collapse" data-target="#paypal">
                            Pay with Paypal
                        </button>
                        <button type="button" class="btn btn-white border rounded border-dark">
                            <a href="./order.jsp">Cancel payment</a>
                        </button>
                    </div>
                    <div id="creditcard" class="collapse show active bg-white rounded border border-warning m-3 p-3">
                        <form method="post" action="PaymentServlet">
                            <div class="form-group text-left">
                                <label for="name">Name:</label>
                                <input class="form-control" type="text" placeholder="John Smith" name="name">
                            </div>
                            <div class="form-group text-left">
                                <label for="cc-number">Card number</label><br />
                                <input class="form-control" id="cc-number" name="cc-number" type="tel" placeholder="0000-0000-0000-0000">
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group text-left">
                                        <label for="expiry-date">Expiry date</label><br />
                                        <input class="form-control" id="expiry-date" name="expiry-date" type="tel" placeholder="MM/YYYY">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group text-left">
                                        <label for="cvv">CVV</label><br />
                                        <input class="form-control" id="cvv" name="cvv" type="tel" placeholder="000">
                                    </div>
                                </div>
                            </div>
                            <p class="text-center text-danger"><%=(emptyErr != null ? emptyErr : "")%></p>
                            <p class="text-center text-danger"><%=(nameErr != null ? nameErr : "")%></p>
                            <p class="text-center text-danger"><%=(numberErr != null ? numberErr : "")%></p>
                            <p class="text-center text-danger"><%=(cvvErr != null ? cvvErr : "")%></p>
                            <p class="text-center text-danger"><%=(dateErr != null ? dateErr : "")%></p>
                            <p class="text-center text-danger"><%=(managerErr != null ? managerErr : "")%></p>
                            <input type="hidden" id="paymentConfirm" name="paymentConfirm" value="false"/> 
                            <input type="hidden" id="paymentCreditCard" name="paymentCreditCard" value="true"/> 
                            <input type="hidden" id="paymentPaypal" name="paymentPaypal" value="false"/> 
                            <button type="submit" class="btn btn-primary" value="paymentContinue" name="submit">Process payment</button>
                        </form>
                    </div>
                    <div id="paypal" class="collapse bg-white rounded border border-danger m-3 p-3">
                        <p>Paypal is currently disabled. Please purchase using a credit card.</p>
                    </div>
                </div>
                <div class="col-md-3"   style="background-color:#a5ebff"></div>
            </div>
            <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
