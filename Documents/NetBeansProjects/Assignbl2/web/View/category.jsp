
<%-- 
    Document   : category
    Created on : Mar 15, 2024, 11:35:38 AM
    Author     : TienP
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Book4Nerds</title>

    </head>
    <body>

        <!-- header section starts  -->
        <jsp:include page="header.jsp"/>

        <div class="container-cate">
            <div class='row'>
                <div class="category-left">
                    <ul>
                        <c:forEach items="${requestScope.category}" var="ct">
                            <li><a href="Category?cateId=${ct.getCateID()}" class="category-left-li">${ct.getNameCate()}</a></li>

                        </c:forEach> 
                    </ul>
                </div>
                <div class='category-right' >
                    <div class='category-right-top row' >
                        <div class="category-right-top-item">
                            <p>Category</p>
                        </div>
                        <div class="category-right-top-item">
                            <!-- <button><span>Bo loc</span><i class="fa-solid fa-arrow-down"></i></button> -->
                        </div>
                        <div class="category-right-top-item">
                            <form action="Sort" method="get" id="orderForm">
                                <select name="order" id="orderSelect" onchange="submitForm()">                                                                                                          
                                            <option>${requestScope.sort}</option>
                                            <option value="new">Newest</option>
                                            <option value="lth">Price:Low to High</option>
                                            <option value="htl">Price:High to Low</option>                                                                        
                                </select>
                            </form>
                        </div>
                    </div>
                    <div class="category-right_content ">
                        <c:forEach items="${requestScope.books}" var="book">
                            <a class="category-right_a" href="details?id=${book.getId()}">
                                <div class='category-right_content-item '>
                                    <img src="${book.image}" alt="alt"/> 
                                    <h1>${book.name}</h1>
                                    <p class="pricep">${book.price}$</p>
                                </div>  
                            </a>
                        </c:forEach>                                                 
                    </div>
                </div>
            </div>
        </div>


        <jsp:include page="footer.jsp"/>

        <!-- footer section ends -->

        <!-- loader  -->




        <script src="https://unpkg.com/swiper@7/swiper-bundle.min.js"></script>
        <script>
                                                    function submitForm() {
                                                        document.getElementById("orderForm").submit();
                                                    }
                                                    $('.category-right_content-item').on('click', function () {
                                                        $(this).addClass('visited');
                                                    });
        </script>

        <!-- custom js file link  -->
        <script src="js/script.js"></script>
        <script src="https://kit.fontawesome.com/54e6ef273a.js" crossorigin="anonymous"></script>
    </body>
</html>    
