<%-- 
    Document   : customerDelete
    Created on : Jun 9, 2020, 4:48:17 AM
    Author     : kamenriderfourze
--%>

<%@page import="uts.wsd.model.Customer"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Delete</title>
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top">
            <span class="navbar-brand mb-0 h1">IoTBay</span>
        </nav>
        <main class = "container-fluid main_container" style="background-color:#a5ebff">
            <%
                String deleteCustomer = (String) session.getAttribute("deleteCustomer");
                Customer customer = (Customer) session.getAttribute("customer");
                
            %>
            <div class="row d-flex p-2" >
                <div class="col-md-3"></div>
                <div class="col-md-6 p-5 bg-white rounded border">
                    <h1><%=(deleteCustomer != null ? deleteCustomer : "Are you sure you want to delete this Customer: " + customer.getName()) + "?"%> </h1>

                    <a href="customerDeleteServlet?editID=${customer.getUserID()}" class="btn btn-danger" role="button" aria-pressed="true">Delete Customer</a>
                    <a href="customer.jsp" class="btn btn-primary btn-md">Return to main page</a>

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