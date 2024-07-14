<%-- 
    Document   : recordTrans
    Created on : Mar 18, 2024, 9:32:25 AM
    Author     : TienP
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transaction Records Page</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>        
        <style>
            .category-right{
                margin-left: 50px;
            }
        </style>
        <link rel="stylesheet" href="css/modal.css">
    </head>
    <body>
        <jsp:include page='header.jsp'/>

    <div class="container-cate">
        <div class='row'>
            
            <div class='category-right' >
                <div class="container-xl">
                    <div class="table-responsive">
                        <div class="table-wrapper">
                            <div class="table-title">
                                <div class="row">
                                    <div class="col-sm-8"><h2>Book <b>Manager</b></h2></div>
                                   
                                    </div>
                                </div>
                            </div>
                            <table class="table table-striped table-hover table-bordered">
                                 <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Image</th>
                                            <th>Quantity</th>
                                            <th>Address</th>
                                            <th>Date</th>
                                            <th>Price</th>
                                            <th>Status</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.list}" var="od">
                                            <tr>
                                                <td>${od.getBookid().getName()}</td>
                                                <td><<img src="${od.getBookid().getImage()}" alt="alt"/></td>
                                                <td>${od.getQuantity()}</td>
                                                <td>${od.getAddress()}</td>
                                                <td>${od.getDate()}</td>
                                                <td>${od.getPrice()}</td>
                                                <td>${od.getStatus()}</td>
                                                <td></td>

                                            </tr>
                                        </c:forEach>

                                    </tbody>
                            </table>

                        </div>
                    </div>  
                </div>
            </div>
        </div>
    </div>

       
        <jsp:include page='footer.jsp'/>
    </body>
</html>
