<%@page import="uts.wsd.model.Staff"%>
<%@page import="uts.wsd.model.Customer"%>
<%@page import="uts.wsd.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Edit Profile Page</title>
        <link rel="stylesheet" href="style.css">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>
        <%
            String existErr = (String) session.getAttribute("existErr");
            String updatedUserErr = (String) session.getAttribute("updatedUserErr");
            String updatedUserSuccess = (String) session.getAttribute("updatedUserSuccess");
            User user = (User) session.getAttribute("user");
            Customer customer = (Customer) session.getAttribute("customer");
            Staff staff = (Staff) session.getAttribute("staff");
            int userID = 0;
            String name = "";
            String phone = "";
            String email = "";
            String password = "";
            if (user != null) {
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


        <div class="row d-flex p-2 text-center w-100 h-100" style="background-color:#a5ebff">
            <div class="col-md-3"></div>
            <div class="col-md-6 p-5 bg-white rounded border">
                <h1 class="text-center">Edit Profile</h1>
                <p class="text-center text-danger"><%=(updatedUserErr != null ? updatedUserErr : "")%></p>
                <p class="text-center text-success"><%=(updatedUserSuccess != null ? updatedUserSuccess : "")%></p>
                <h2 class="text-center"></h1>
                    <form method="post" action="updateUserServlet">
                        <%
                            email = user.getEmail();
                            userID = user.getUserID();
                            password = user.getPassword();
                            if (customer != null) {
                                name = customer.getName();
                                phone = customer.getPhone();
                            } else if (staff != null) {
                                name = staff.getName();
                                phone = staff.getPhone();
                            }
                        %>
                        <div class="form-group text-left">
                            <label for="userID">UserID: </label>
                            <input class="form-control" type="text" name="userID" value="<%= userID%>" readonly>
                        </div>
                        <div class="form-group text-left">
                            <label for="name">Name:</label>
                            <input class="form-control" type="text" placeholder="Enter name:" name="name" value="<%=name%>">
                        </div>
                        <div class= "form-group ">
                            <label for="email">Email address:</label>
                            <input class="form-control" type="text" placeholder="Enter email address:" name="email" value="<%=email%>">
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input class="form-control" type="password" placeholder="Enter password:" name="password" value="<%=password%>">
                        </div>
                        <div class ="form-group">
                            <label for="phone">Phone Number</label>
                            <input class="form-control" type="text" placeholder="Enter phone" name="phone" value="<%=phone%>">
                        </div>
                        <button type="submit" class="btn btn-primary" value ="Update" name ="submit">Update</button>
                    </form>
                    <br>    
                    <a href="deleteUser.jsp" class="btn btn-danger" role="button" aria-pressed="true">Delete Profile</a>
            </div>
            <div class="col-md-3"></div>
        </div>
        <%
        } else {
        %>
        <nav class="navbar navbar-expand-md navbar-light bg-light sticky-top">

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
                </ul>
            </div>
        </nav>

        <div class="row d-flex p-2 text-center w-100 h-100" style="background-color:#a5ebff">
            <div class="col-md-3"></div>
            <div class="col-md-6 p-5 bg-white rounded border">
                <h1 class="text-center">Unauthorized Access</h1>
                <h2 class="text-center">Please login or register to access this page</h2>
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

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>