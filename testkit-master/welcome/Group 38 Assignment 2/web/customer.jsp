<%-- 
    Document   : customer
    Created on : 7 Jun 2020, 9:55:35 pm
    Author     : samuelli
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
        <title>View Customer Page</title>
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.16.0/dist/bootstrap-table.min.css">
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
                    <li class="nav-item">
                        <a class="nav-link" href="addCustomer.jsp">Add Customer</a>
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
            <div class="col-md-1"></div>
            <div class="col-md-10 p-5 bg-white rounded border">
            

        
                <table class="table table-hover" data-toggle="table" data-search="true">
                    <thead>
                        <tr>
                            <th scope="col" class="w-25"  data-field="custID">Customer ID</th>
                            <th scope="col" class="w-25"  data-field="name">Name</th>
                            <th scope="col" class="w-25" data-field="phone">Phone</th>
                            <th scope="col" data-field="userID">UserID</th>
                            <th scope="col" data-field="status">Status</th>
                             <th scope="col" data-field="editable"></th>
                            <th scope="col" data-field="delete"></th>
                               <th scope="col" data-field="changeStatus"></th>
                           
                        </tr>
                    </thead>
                    <tbody class="searchable">
                        <c:forEach items="${customerList}" var="cList" >
                            <tr>
                                <td><c:out value="${cList.custID}"/></td>
                                <td><c:out value="${cList.name}"/></td>
                                <td><c:out value="${cList.phone}"/></td>
                                <td><c:out value="${cList.userID}"/></td>
                                <td><c:out value="${cList.status}"/></td>
                                 <td>
                                    <div>
                                 <a href="dataLoadCustomerServlet?editCustomerID=${cList.custID}"  class="btn btn-primary btn-lg" role="button" aria-pressed="true">Edit</a>
                       

                                    </div>
                                </td>
                                <td>
                                    <div>
                                        <a href="customerDeleteServlet?editID=${clist.userID}" class="btn btn-danger btn-lg" role="button" aria-pressed="true">Delete</a

                                    </div>
                                </td>
                             
                               
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>
            <div class="col-md-1"></div>
        </div>
        <footer class="footer"></footer>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
        <script src="https://unpkg.com/bootstrap-table@1.16.0/dist/bootstrap-table.min.js"></script>
    </body>
</html>
