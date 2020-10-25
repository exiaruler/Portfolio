<%-- 
    Document   : editProduct
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
        <title>Edit Product Page</title>
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
                    User user = (User) session.getAttribute("user");

                    Staff staff = (Staff) session.getAttribute("staff");
                    String existErr = (String) session.getAttribute("existErr");
                    String prdErr = (String) session.getAttribute("prdErr");
                    String typeErr = (String) session.getAttribute("typeErr");
                    String stockErr = (String) session.getAttribute("stockErr");
                    String brandErr = (String) session.getAttribute("brandErr");
                    String priceErr = (String) session.getAttribute("priceErr");
                    String descErr = (String) session.getAttribute("descErr");
                    String emptyErr = (String) session.getAttribute("emptyErr");
                    String edited = (String) session.getAttribute("edited");
                    Product product = (Product) session.getAttribute("product");

                %>
                <h1 class="text-center">Edit product</h1>
                <p class="text-center text-danger"><%=(existErr != null ? existErr : "")%></p>
                <p class="text-center text-danger"><%=(emptyErr != null ? emptyErr : "")%></p>

                <p class="text-center text-danger"><%=(edited != null ? edited : "")%></p>
                <p class="text-center text-danger"><%=(prdErr != null ? prdErr : "")%></p>
                <p class="text-center text-danger"><%=(typeErr != null ? typeErr : "")%></p>
                <p class="text-center text-danger"><%=(stockErr != null ? stockErr : "")%></p>
                <p class="text-center text-danger"><%=(descErr != null ? descErr : "")%></p>
                <p class="text-center text-danger"><%=(priceErr != null ? priceErr : "")%></p>
                <p class="text-center text-danger"><%=(brandErr != null ? brandErr : "")%></p>
     
                
                <%
                    if (staff != null) {
                %>

                <p><c:out value="${requestScope.product }"/></p>
                <form method="post" action="editProductServlet">
                    <div class="form-group text-left">
                        <label for="name">ID</label>
                        <input class="form-control" type="text" value = "${product.getProductID()}" name="id">
                    </div>
                    <div class="form-group text-left">
                        <label for="name">Name:</label>
                        <input class="form-control" type="text" value = "${product.getProductName()}" name="name">
                    </div>



                    <div class="form-group text-left">
                        <label for="price">Price</label>
                        <input class="form-control" type="text" value = "${product.getProductPrice()}" name="price">
                    </div>

                    <div class ="form-group text-left">
                        <label for="desc">Description</label>
                        <input class="form-control" type="text" value = "${product.getProductDescription()}" name="desc">
                    </div>

                    <div class ="form-group text-left">
                        <label for="stock">Stock</label>
                        <input class="form-control" type="text" value = "${product.getProductStock()}" name="stock">
                    </div>


                    <div class= "form-group text-left">
                        <label for="type">Type</label>
                        <input class="form-control" type="text" value = "${product.getProductType()}" name="type">
                    </div>

                    <div class ="form-group text-left">
                        <label for="brand">Brand</label>
                        <input class="form-control" type="text"  value = "${product.getProductBrand()}" name="brand">
                    </div>



                    <button type="submit" class="btn btn-success" value ="editProduct" name ="submit">Edit product</button>
                      <a href="ProductListServlet" class="btn btn-secondary btn-md" role="button" aria-pressed="true">Back to products</a>

                    <a href="./index.jsp" class="btn btn-primary btn-md" role="button" aria-pressed="true">Back to Home</a>
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
