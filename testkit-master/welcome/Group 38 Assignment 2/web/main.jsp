<%-- 
    Document   : main
    Created on : 09/04/2020, 10:26:57 AM
    Author     : Eric
--%>

<%@page import="uts.wsd.model.Staff"%>
<%@page import="uts.wsd.model.Customer"%>
<%@page import="uts.wsd.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Page</title>
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
            Customer customer = null;
            Staff staff = null;
            String name = "";
            String phone = "";
            if (session.getAttribute("customer") != null) {
                customer = (Customer) session.getAttribute("customer");
                name = customer.getName();
                phone = customer.getName();
            } else if (session.getAttribute("staff") != null) {
                staff = (Staff) session.getAttribute("staff");
                name = staff.getName();
                phone = staff.getName();
            }
        %>
        <nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top" >
            <span class="navbar-brand mb-0 h1">IoTBay</span>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <%
                    if (user != null) {
                %>
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
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
                        <a class="nav-link" href="viewLogsServlet?userID=<%= user.getUserID()%>">View Access Logs</a>
                    </li>
                    <li class="nav-item ">
                        <a class="nav-link" href="editUserServlet?userID=<%= user.getUserID()%>">Profile</a>
                    </li>
                    <li class="nav-item ">
                        <a class="nav-link " href="LogoutServlet?userID=<%= user.getUserID()%>">Logout</a>
                    </li>
                </ul>

                <%
                } else {
                %>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="index.jsp">Home</a>
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
                </ul>
                <%
                    }
                %>
            </div>
        </nav>
        <div class="row d-flex p-2  text-center w-100 h-100" style="background-color:#a5ebff">
            <div class="col-md-3"></div>
            <div class="col-md-6 p-5 bg-white rounded border">
                <%
                    if (user != null) {
                %>        
                <header class="card-header">
                    <div class="container">
                        <p>
                            You are logged in as <%= user.getEmail()%>.
                        </p>
                    </div>
                </header>

                <table class="table table-hover"">
                    <caption>User Profile Details</caption>
                    <thead class="table-info">
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Email</th>
                            <th scope="col">Password</th>
                            <th scope="col">Phone</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><%= name%></td>
                            <td><%= user.getEmail()%></td>
                            <td><%= user.getPassword()%></td>
                            <td><%= phone%></td>
                        </tr>
                    </tbody>
                </table>
                <%
                } else {
                %>
                <header class="card-header">
                    <div class="container">
                        <p>
                            You are logged in as a guest.
                        </p>
                    </div>
                </header>
                <%
                    }
                %>
            </div>
            <div class="col-md-3"></div>
        </div>
        <footer class="card-footer">
            <div class="container">
                <span class="text-muted">
                    IoTBay 2020
                </span>
            </div>
        </footer>
    </body>
</html>
