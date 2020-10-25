<%-- 
    Document   : register
    Created on : 28/05/2020, 10:26:57 AM
    Author     : Sheng
--%>

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
        <title>Add Product Page</title>
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
                        <a class="nav-link " href="logout.jsp">Logout</a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="row d-flex p-2  text-center min-vh-100" style="background-color:#a5ebff">
            <div class="col-md-3"></div>
            <div class="col-md-6 p-5 bg-white rounded border">
                <%

                    String existErr = (String) session.getAttribute("existErr");
                    String prdErr = (String) session.getAttribute("prdErr");
                    String typeErr = (String) session.getAttribute("typeErr");
                    String stockErr = (String) session.getAttribute("stockErr");
                    String brandErr = (String) session.getAttribute("brandErr");
                    String priceErr = (String) session.getAttribute("priceErr");
                    String descErr = (String) session.getAttribute("descErr");
                    String emptyErr = (String) session.getAttribute("emptyErr");
                    String added = (String) session.getAttribute("added");
                    User user = (User) session.getAttribute("user");

                    Staff staff = (Staff) session.getAttribute("staff");
                %>
                <h1 class="text-center">Add product</h1>
                <h2 class="text-center"><%=(existErr != null ? existErr : "")%></h2>
                <h2 class="text-center"><%=(emptyErr != null ? emptyErr : "")%></h2>
                <h2 class="text-center"><%=(added != null ? added : "")%></h2>

                <%
                    if (staff != null) {
                %>

                <form method="post" action="addProductServlet">
                    <div class="form-group text-left">
                        <label for="name">Name:</label>
                        <input class="form-control" type="text" placeholder="<%=(prdErr != null ? prdErr : "Enter name")%>" name="name">
                    </div>

                    <div class= "form-group text-left">
                        <label for="type">Type</label>
                        <input class="form-control" type="text" placeholder="<%=(typeErr != null ? typeErr : "Enter type")%>" name="type">
                    </div>

                    <div class="form-group text-left">
                        <label for="price">Price</label>
                        <input class="form-control" type="text" placeholder="<%=(priceErr != null ? priceErr : "Enter price")%>" name="price">
                    </div>

                    <div class ="form-group text-left">
                        <label for="desc">Description</label>
                        <input class="form-control" type="text" placeholder="<%=(descErr != null ? descErr : "Enter description")%>" name="desc">
                    </div>

                    <div class ="form-group text-left">
                        <label for="brand">Brand</label>
                        <input class="form-control" type="text" placeholder="<%=(brandErr != null ? brandErr : "Enter brand")%>" name="brand">
                    </div>

                    <div class ="form-group text-left">
                        <label for="stock">Stock</label>
                        <input class="form-control" type="text" placeholder="<%=(stockErr != null ? stockErr : "Enter stock")%>" name="stock">
                    </div>

                    <button type="submit" class="btn btn-success" value ="addProduct" name ="submit">Add product</button>
                    <a href="ProductListServlet" class="btn btn-secondary btn-md" role="button" aria-pressed="true">Back to products</a>
                     
                </form>
                <%
                } else {
                %>
                <div>
                    <p>You are not authorised to view this page.</p>
                    <a href="./index.jsp" class="btn btn-primary btn-md" role="button" aria-pressed="true">Back to Home</a>
                      <a href="ProductListServlet" class="btn btn-secondary btn-md" role="button" aria-pressed="true">Back to products</a>
                </div>
                <%
                    }
                %>
            </div>
            <div class="col-md-3"></div>
        </div>


        <footer class="footer"></footer>
    </body>
</html>
