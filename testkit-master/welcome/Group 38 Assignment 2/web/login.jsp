<%@page import="uts.wsd.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Login Page</title>
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>
        <%
            String existErr = (String) session.getAttribute("existErr");
            String emailErr = (String) session.getAttribute("emailErr");
            String passErr = (String) session.getAttribute("passErr");
            String managerErr = (String) session.getAttribute("managerErr");
            User user = (User) session.getAttribute("user");
            if (user == null) {
        %>
        <nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top">
            <span class="navbar-brand mb-0 h1">IoTBay</span>
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
                </ul>
            </div>
        </nav>

        <div class="row d-flex p-2 text-center w-100 h-100" style="background-color:#a5ebff">
            <div class="col-md-3"></div>
            <div class="col-md-6 p-5 bg-white rounded border mh-100">
                <h1 class="text-center">Login</h1>
                <h2 class="text-center"><%=(existErr != null ? existErr : "")%></h2>
                <h2 class="text-center"><%=(managerErr != null ? existErr : "")%></h2>
                <form method="post" action="LoginServlet">
                    <div class= "form-group">
                        <label for="email">Email address</label>
                        <input class="form-control" type="text" placeholder="<%=(emailErr != null ? emailErr : "Enter email")%>" name="email">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input class="form-control" type="password" placeholder="<%=(passErr != null ? passErr : "Enter password")%>" name="password">
                    </div>
                    <input class="btn btn-success" type = "submit" value = "Login" name = "submit">
                    <a href="./index.jsp" class="btn btn-secondary btn-md" role="button" aria-pressed="true">Back to Home</a>
                </form>
            </div>
            <div class="col-md-3"></div>
        </div>

        <%
        } else {
        %>
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
                        <a class="nav-link" href="main.jsp">Main</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="ProductListServlet">Product</a>
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
            </div>
        </nav>
        <div class="row d-flex p-2 text-center w-100 h-100" style="background-color:#a5ebff">
            <div class="col-md-3"></div>
            <div class="col-md-6 p-5 bg-white rounded border mh-100">
                <h1 class="text-center">You are already logged in.</h1>
                <h2 class="text-center">Please return to the main page or logout.</h2>
                <div class=" btn-group-justified">
                    <a href="./main.jsp" class="btn btn-primary btn-lg" role="button" aria-pressed="true">Main</a>
                    <a href="LogoutServlet?userID=<%= user.getUserID()%>" class="btn btn-success btn-lg" role="button" aria-pressed="true">Logout</a>
                </div>
            </div>
            <div class="col-md-3"></div>
        </div>
        <%
            }
        %>
        <footer class="card-footer">
            <div class="container">
                <span class="text-muted">
                    IoTBay 2020
                </span>
            </div>
        </footer>

    </body>
</html>
