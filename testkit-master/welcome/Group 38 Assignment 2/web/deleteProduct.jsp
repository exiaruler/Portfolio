<%-- 
    Document   : deleteProduct
    Created on : 07/06/2020, 7:35:30 PM
    Author     : Sheng
--%>

<%@page import="uts.wsd.model.User"%>
<%@page import="uts.wsd.model.Staff"%>
<%@page import="uts.wsd.model.Product"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Delete Success</title>
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
        <main class = "container-fluid main_container" style="background-color:#a5ebff">
            <%
                String deleteProd = (String) session.getAttribute("deleteProd");
                Product product = (Product) session.getAttribute("product");
                User user = (User) session.getAttribute("user");

                Staff staff = (Staff) session.getAttribute("staff");
            %>
            <div class="row d-flex p-2 min-vh-100" >
                <div class="col-md-3"></div>
                <div class="col-md-6 p-5 bg-white rounded border">
                    <%
                        if (staff != null) {
                    %>
                    <p class="text-danger"><%=(deleteProd != null ? deleteProd : "Are you sure you want to delete this product: " + product.getProductName()) + "?"%> </p>
                    <a href="deleteProductServlet?editProductID=${product.getProductID()}" class="btn btn-danger" role="button" aria-pressed="true">Delete Product</a>
                    <a href="index.jsp" class="btn btn-primary btn-md">Return to Home page</a>
                      <a href="ProductListServlet" class="btn btn-primary btn-md" role="button" aria-pressed="true">Back to products</a>
                    <%
                    } else {
                    %>
                    <div>
                        <p>You are not authorised to view this page.</p>
                        <a href="./index.jsp" class="btn btn-secondary btn-md" role="button" aria-pressed="true">Back to Home</a>
                          <a href="ProductListServlet" class="btn btn-secondary btn-md" role="button" aria-pressed="true">Back to products</a>
                    </div>
                    <%
                        }
                    %>
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
</html>
