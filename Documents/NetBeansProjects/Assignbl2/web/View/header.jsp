
<%-- 
    Document   : header
    Created on : Mar 16, 2024, 8:21:19 PM
    Author     : TienP
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Book4Nerds</title>

        <link rel="stylesheet" href="https://unpkg.com/swiper@7/swiper-bundle.min.css" />

        <!-- font awesome cdn link  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

        <!-- custom css file link  -->
        <link rel="stylesheet" href="css/style.css?id=1234">

        <link rel="apple-touch-icon" sizes="180x180" href="favicon_io/apple-touch-icon.png">
        <link rel="icon" type="image/png" sizes="32x32" href="favicon_io/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="16x16" href="favicon_io/favicon-16x16.png">
        <link rel="manifest" href="favicon_io/site.webmanifest">
    </head>
    <body>

        <!-- header section starts  -->

        <header class="header">

            <div class="header-1">

                <a href="Home" class="logo"> <i class="fas fa-book"></i> Books4Nerds </a>

                <form action="Category" method="post" class="search-form">
                    <input type="search" name="keyword" placeholder="search here..." id="search-box">
                    <label for="search-box" ><button type="submit" name="submit" value="search" class="fas fa-search"></button></label>
                    
                </form>

                <div class="icons">
                    <div id="search-btn" class="fas fa-search"></div>
                    <a href="Cart" class="fas fa-shopping-cart"></a>
                    <c:choose>
                        <c:when test="${sessionScope.account ne null}"><a href="LogOut" class="fa-solid fa-right-from-bracket"></a></c:when> 
                        <c:otherwise><a href="Login" class="fa-solid fa-right-to-bracket"></a></c:otherwise>    
                    </c:choose>



                </div>

            </div>

            <div class="header-2">
                <nav class="navbar">
                    <a href="Home">home</a>                   
                    <a href="Category">Category</a>  
                    <a href="View/review.jsp">review</a>
                    <a href="View/feedback.jsp">feedback</a>

                    <c:choose>
                        <c:when test="${sessionScope.account.getRole() == 0}">
                            <a href="ManagerProduct">Manager Products</a>
                        </c:when>
                        <c:when test="${sessionScope.account.getRole() == 1}">                         
                                <a href="history">Transaction History</a>                         
                        </c:when>
                    </c:choose>


                </nav>
            </div>

        </header>
        <script src="https://unpkg.com/swiper@7/swiper-bundle.min.js"></script>

        <!-- custom js file link  -->
        <script src="js/script.js"></script>
        <script src="https://kit.fontawesome.com/54e6ef273a.js" crossorigin="anonymous"></script>
    </body>
</html>