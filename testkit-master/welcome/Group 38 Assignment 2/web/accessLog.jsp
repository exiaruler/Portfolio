<%@page import="uts.wsd.model.AccessLogs"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uts.wsd.model.Staff"%>
<%@page import="uts.wsd.model.Customer"%>
<%@page import="uts.wsd.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Application Access Log</title>
        <link rel="stylesheet" href="style.css">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>
        <%
            ArrayList<AccessLogs> userAccessLog = (ArrayList<AccessLogs>) session.getAttribute("accessLog");
            String logDateErr = (String) session.getAttribute("logDateErr");
            User user = (User) session.getAttribute("user");
            if (user != null) {
                int userID = user.getUserID();
        %>
        <nav class="navbar navbar-expand-md navbar-light bg-light sticky-top">
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
            </div>
        </nav>

        <div class="row d-flex p-2  text-center w-100 h-100" style="background-color:#a5ebff">
            <div class="col-md-1"></div>
            <div class="col-md-10 p-5 bg-white rounded border">
                <h1 class="text-center">Application Access Log</h1>

                <form method="post" action="searchLogsServlet">
                    <div class="form-group text-left">
                        <label for="userID">UserID: </label>
                        <input class="form-control" type="text" name="userID" value="<%= userID%>" readonly>
                    </div>
                    <div class="form-group text-left">
                        <label for="date">Search by Date (dd/MM/yyyy):</label>
                        <input class="form-control" type="text" placeholder="<%=(logDateErr != null ? logDateErr : "")%>" name="date">
                    </div>
                    <input class="btn btn-success" type = "submit" value = "SearchDate" name = "submit">
                </form>
                <br/>
                <a href="viewLogsServlet?userID=<%= user.getUserID()%>" class="btn btn-primary btn-lg">View all</a>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th scope="col">Access ID</th>
                            <th scope="col">User ID</th>
                            <th scope="col">Date of Action (dd/MM/yyyy)</th>
                            <th scope="col">Time of Action (HH:mm)</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (AccessLogs userLogs : userAccessLog) {
                        %>
                        <tr>
                            <td><%= userLogs.getAccessID()%></td>
                            <td><%= userLogs.getUserID()%></td>
                            <td><%= userLogs.getDateAccessed()%></td>
                            <td><%= userLogs.getTimeAccessed()%></td>
                            <td><%= userLogs.getAction()%></td>
                        </tr>
                        <%
                            }
                        %>

                    </tbody>
                </table>
            </div>
            <div class="col-md-1"></div>
        </div>
        <%
        } else {
        %>

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

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>