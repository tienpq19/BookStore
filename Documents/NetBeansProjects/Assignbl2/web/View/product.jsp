<%-- 
    Document   : product
    Created on : Mar 15, 2024, 6:09:41 AM
    Author     : TienP
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <style>
            .active{
                max-width: 300px;
                max-height: 700px;
                padding: 5px;
            }
        </style>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product </title>
        <!-- Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" rel="stylesheet">
        <!-- CSS -->
        <link href="style.css" rel="stylesheet">
        <meta name="robots" content="noindex,follow" />
        <link rel="stylesheet" href="https://unpkg.com/swiper@7/swiper-bundle.min.css" />

        <!-- font awesome cdn link  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

        <!-- custom css file link  -->
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="./css/product.css">

        <link rel="apple-touch-icon" sizes="180x180" href="favicon_io/apple-touch-icon.png">
        <link rel="icon" type="image/png" sizes="32x32" href="favicon_io/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="16x16" href="favicon_io/favicon-16x16.png">
        <link rel="manifest" href="favicon_io/site.webmanifest">
    </head>
    <body>

        <!-- header section starts  -->

        <jsp:include page="header.jsp"/>

        <!-- header section ends -->

        <!-- bottom navbar  -->


        <main class="container">
            <c:set value="${requestScope.book}" var="book"/>
            <!-- Left Column / Headphones Image -->
            <div class="left-column">
                <img data-image="red" class="active" src="${book.getImage()}" alt="">
            </div>


            <!-- Right Column -->
            <div class="right-column">

                <!-- Product Description -->
                <div class="product-description">
                    <span>Books</span>
                    <h1>${book.getName()}</h1>
                    <h2>${book.getAuthor()}</h2>
                    <p>Publisher: ${book.getPublisher()}</p>
                    <p>Page: ${book.getPage()}</p>
                    <p>Language: ${book.getLanguage()}</p>
                    <p>Category: ${book.getCategory().getNameCate()}</p>
                    <p>Description: ${book.getDescrip()}</p>
                    <p>In stock: ${book.getQuantity()}</p>
                </div>

                <!-- Product Configuration -->
                <div class="product-configuration">
                </div>

                <!-- Product Pricing -->
                <div class="product-price">
                    <span>${book.getPrice()}$</span>
                    <c:choose>
                        <c:when test="${book.getQuantity() eq 0}"><p class="btn">Out Stock!</p></c:when> 
                        <c:otherwise>
                            <div class="quantity">
                                <input name="quantity" type="number" class="quantity-input" value="1" min="1" max="${book.getQuantity()}"
                                       onchange="validateMaxQuantity(this)">
                                <button class="quantity-btn" onclick="increaseQuantity()">+</button>
                                <button class="quantity-btn" onclick="decreaseQuantity()">-</button>
                            </div>
                            <c:choose>
                                <c:when test="${sessionScope.account ne null}">
                                    <button class="btn" onclick="addToCart('${book.getId()}')">Add to cart ss </button>
                                </c:when> 
                                <c:otherwise>
                                    <button class="btn" onclick="addToCartck('${book.getId()}')">Add to cart ck </button>
                                </c:otherwise>    
                            </c:choose>


                        </c:otherwise>    
                    </c:choose>

                </div>
            </div>
        </main>

        <!-- footer section starts  -->
        <jsp:include page="footer.jsp"/>

        <!-- footer section ends -->
        <script>

//            function addToCart(id) {
//                var quantityInput = document.querySelector('.quantity-input');
//                var quantity = quantityInput.value;
//
//                event.preventDefault();
//
//
//                fetch(`Cart4Acc?num=` + quantity + `&id=` + id, {
//                    method: "GET"
//                })
//                        .then(response => response.text())
//                        .then(data => {
//                            console.log(data);
//                            alert("Product added to cart!");
//                        })
//                        .catch((error) => {
//                            console.error('Error:', error);
//                        });
//            }
            function addToCart(id) {
                var quantityInput = document.querySelector('.quantity-input');
                var quantity = quantityInput.value;
                // Ngăn chặn hành động mặc định của nút button (không gửi yêu cầu form)
                event.preventDefault();

                // Sử dụng let hoặc const để khai báo biến

                fetch(`Cart4Acc?num=` + quantity + `&id=` + id, {// Sử dụng giá trị của quantity và id trong URL
                    method: "GET"
                })
                        .then(response => response.text())
                        .then(data => {
                            console.log(data); // Log phản hồi từ servlet
                            alert("Product added to cart!"); // Thông báo cho người dùng
                        })
                        .catch((error) => {
                            console.error('Error:', error);
                        });
            }
            function addToCartck(id) {
                var quantityInput = document.querySelector('.quantity-input');
                var quantity = quantityInput.value;
                // Ngăn chặn hành động mặc định của nút button (không gửi yêu cầu form)
                event.preventDefault();

                // Sử dụng let hoặc const để khai báo biến

                fetch(`CartDetails?num=` + quantity + `&id=` + id, {// Sử dụng giá trị của quantity và id trong URL
                    method: "GET"
                })
                        .then(response => response.text())
                        .then(data => {
                            console.log(data); // Log phản hồi từ servlet
                            alert("Product added to cart!"); // Thông báo cho người dùng
                        })
                        .catch((error) => {
                            console.error('Error:', error);
                        });
            }

            function increaseQuantity() {
                var quantityInput = document.querySelector('.quantity-input');
                var currentQuantity = parseInt(quantityInput.value);
                var maxQuantity = parseInt(quantityInput.max);

                if (currentQuantity < maxQuantity) {
                    quantityInput.value = currentQuantity + 1;
                }
            }

            function decreaseQuantity() {
                var quantityInput = document.querySelector('.quantity-input');
                var currentQuantity = parseInt(quantityInput.value);

                if (currentQuantity > 1) {
                    quantityInput.value = currentQuantity - 1;
                }
            }
            function validateMaxQuantity(input) {
        var max = parseInt(input.getAttribute('max'), 10);
        if (input.value > max) {
            input.value = max; // Cập nhật giá trị về max nếu vượt quá
        }
    }
        </script> 

        <!-- Scripts -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js" charset="utf-8"></script>
        <script src="script.js" charset="utf-8"></script>
        <script src="./js/product.js"></script>
    </body>
</html>

