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
    </head>
    <body>
        <jsp:include page='header.jsp'/>



        <div class="container-cate">
            <div class='row'>
                <div class="category-left">
                    <ul>             
                        <li><a href="ManagerProduct" class="category-left-li">Product Management</a></li>
                        <li><a href="recordTrans" class="category-left-li">Transaction Records</a></li>
                        <li><a href="chart" class="category-left-li">Data</a></li>
                    </ul>
                </div>
                <div class='category-right' >
                    <div class="container-xl">
                        <div class="table-responsive">
                            <div class="table-wrapper">
                                <div class="table-title">
                                    <div class="row">
                                        <div class="col-sm-8"><h2>Book <b>Manager</b></h2></div>
                                        <div class="col-sm-4">
                                            <div class="search-container">

                                            </div>
                                            <div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <table class="" style="table-layout: fixed;">
                                    <thead>
                                        <tr>
                                            <th>Order ID</th>
                                            <th>Name</th>                                     
                                            <th>Quantity</th>
                                            <th>Full Name</th>
                                            <th>Phone Number </th>
                                            <th>Address</th>
                                            <th>Price</th>
                                            <th>Date</th>
                                            <th>Pay Method</th>
                                            <th>Status </th>

                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.list}" var="Data" varStatus="status">
                                            <tr>
                                                <td>${Data.getOrderID()}</td>
                                                <td>${Data.getBookid().getName()}</td>

                                                <td>${Data.getQuantity()}</td>
                                                <td>${Data.getName()}</td>
                                                <td>${Data.getPhoneNumber()}</td>
                                                <td>${Data.getAddress()}</td>
                                                <td>${Data.getPrice()}</td>
                                                <td>${Data.getDate()}</td>
                                                <td>${Data.getPayMethod()}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${Data.getStatus() ne 'delivered'}">
                                                            <form action="changeStatus" method="get" id="orderForm${status.index}">
                                                                <input type="hidden" name="id" value="${Data.getOrderID()}">
                                                                <select name="status" id="orderSelect${status.index}" onchange="submitForm('${status.index}')" >
                                                                    <option>${Data.getStatus()}</option>
                                                                    <option value="pending Confirmation">Pending Confirmation</option>
                                                                    <option value="inTransit">In Transit</option>
                                                                    <option value="delivered">Delivered</option>
                                                                    
                                                                </select>  
                                                            </form>
                                                        </c:when> 
                                                        <c:otherwise> ${Data.getStatus()}</c:otherwise>    
                                                    </c:choose>
                                                </td>




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
    <script>
        function submitForm(index) {
            document.getElementById("orderForm" + index).submit();
        }
    </script>

</html>
