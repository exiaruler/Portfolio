<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="uts.wsd.model.Product"%>
<%@page import="uts.wsd.model.Staff"%>
<%@page import="uts.wsd.model.Customer"%>
<%@page import="uts.wsd.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Customer Page</title>
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top" >

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
                <ul class="nav navbar-nav ml-auto">
                    <li class="nav-item ">
                        <a class="nav-link " href="logoutServlet">Logout</a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="row d-flex p-2  text-center w-100 h-100" style="background-color:#a5ebff">
            <div class="col-md-3"></div>
            <div class="col-md-6 p-5 bg-white rounded border">
                <%
                      String emptyErr = (String) session.getAttribute("emptyErr");
                     String existErr = (String) session.getAttribute("existErr");
            String emailErr = (String) session.getAttribute("emailErr");
            String passErr = (String) session.getAttribute("passErr");
            String nameErr = (String) session.getAttribute("nameErr");
            String phoneErr = (String) session.getAttribute("phoneErr");
                    String updated = (String) session.getAttribute("updated");
                %>
                <h1 class="text-center">Update Customer</h1>
                <h2 class="text-center"><%=(existErr != null ? existErr : "")%></h2>
                  <h2 class="text-center"><%=(emptyErr != null ? emptyErr : "")%></h2>
                <h2 class="text-center"><%=(updated != null ? updated : "")%></h2>
                
                <c:forEach items="${requestScope.customer}" var="cList" >
                 <form method="post" action="customerEditServlet">
                        <div class="form-group text-left">
                            <label for="name">Name:</label>
                            <input class="form-control" type="text"  value="${customer.getName()}" name="name">
                        </div>

                        <div class= "form-group text-left ">
                            <label for="email">Email address</label>
                            <input class="form-control" type="text" placeholder="<%=(emailErr != null ? emailErr : "Enter email")%>"  value="${customer.getEmail()}" name="email"></td>
                        </div>

                        <div class="form-group text-left">
                            <label for="password">Password</label>
                            <input class="form-control" type="password" placeholder="<%=(passErr != null ? passErr : "Enter password")%>" value= value="${customer.getPassword()}" name="password">
                        </div>

                  
                        <div class ="form-group text-left">
                            <label for="phone">Phone Number</label>
                            <input class="form-control" type="text" placeholder="<%=(phoneErr != null ? phoneErr : "Enter phone")%>" value="${customer.getPhone()}" name="phone">
                        </div>
                        </c:forEach>
                     
                        <button type="submit" class="btn btn-primary" value ="Register" name ="submit">Update Customer</button>
                        <a href="customerListServlet" class="btn btn-secondary btn-md" role="button" aria-pressed="true">Back to Customer</a>
                    </form>

            </div>
            <div class="col-md-3"></div>
        </div>


        <footer class="footer"></footer>
    </body>
</html>