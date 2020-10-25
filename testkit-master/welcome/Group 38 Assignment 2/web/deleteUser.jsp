<%@page import="uts.wsd.model.User"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Delete Confirm</title>
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
        %>
        <nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top">
            <span class="navbar-brand mb-0 h1">IoTBay</span>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <%
                        if (user == null) {
                    %>
                    <li class="nav-item">
                        <a class="nav-link" href="register.jsp">Register</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="login.jsp">Login</a>
                    </li>
                    <%
                        }
                    %>
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
                    if (user != null) {
                %>
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
                    }
                %>
            </div>
        </nav>

        <div class="row d-flex p-2 text-center w-100 h-100" style="background-color:#a5ebff">
            <div class="col-md-3"></div>
            <div class="col-md-6 p-5 bg-white rounded border">
                <%
                    if (user != null) {
                %>
                <h1>Are you sure you want to delete your profile?</h1>

                <a href="deleteUserServlet?userID=<%= user.getUserID()%>" class="btn btn-danger" role="button" aria-pressed="true">Delete Profile</a>
                <a class="btn btn-primary btn-md" href="editUserServlet?userID=<%= user.getUserID()%>">Return to Profile Page</a>
                <a href="main.jsp" class="btn btn-primary btn-md">Return to main page</a>
                <%
                } else {
                %>
                <h1 class="text-center">Unauthorized Access</h1>
                <h2 class="text-center">Please return to home page</h2>
                <a href="index.jsp" class="btn btn-primary btn-lg" role="button" aria-pressed="true">Return to home page</a>
                <%
                    }
                %>

            </div>
        </div>
        <div class="col-md-3"></div>


        <footer class="card-footer">
            <div class="container">
                <span class="text-muted">
                    IoTBay 2020
                </span>
            </div>
        </footer>
    </body>
</html>
