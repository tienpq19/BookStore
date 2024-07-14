<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >
    <head>
        <meta charset="UTF-8">
        <title>Cart page</title>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js" type="text/javascript"></script>

        <link rel="stylesheet" href="https://unpkg.com/swiper@7/swiper-bundle.min.css" />

        <!-- font awesome cdn link  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <style>
            @import url(https://fonts.googleapis.com/css?family=Droid+Serif:400,400italic|Montserrat:400,700);
            html, body, div, span, applet, object, iframe,
            h1, h2, h3, h4, h5, h6, p, blockquote, pre,
            a, abbr, acronym, address, big, cite, code,
            del, dfn, em, img, ins, kbd, q, s, samp,
            small, strike, strong, sub, sup, tt, var,
            b, u, i, center,
            dl, dt, dd, ol, ul, li,
            fieldset, form, label, legend,
            table, caption, tbody, tfoot, thead, tr, th, td,
            article, aside, canvas, details, embed,
            figure, figcaption, footer, header, hgroup,
            menu, nav, output, ruby, section, summary,
            time, mark, audio, video {
                margin: 0;
                padding: 0;
                border: 0;
                font: inherit;
                font-size: 100%;
                vertical-align: baseline;
            }

            html {
                line-height: 1;
            }

            ol, ul {
                list-style: none;
            }

            table {
                border-collapse: collapse;
                border-spacing: 0;
            }

            caption, th, td {
                text-align: left;
                font-weight: normal;
                vertical-align: middle;
            }

            q, blockquote {
                quotes: none;
            }
            q:before, q:after, blockquote:before, blockquote:after {
                content: "";
                content: none;
            }

            a img {
                border: none;
            }

            article, aside, details, figcaption, figure, footer, header, hgroup, main, menu, nav, section, summary {
                display: block;
            }

            * {
                box-sizing: border-box;
            }

            body {
                color: #333;
                -webkit-font-smoothing: antialiased;
                font-family: "Droid Serif", serif;
            }

            img {
                max-width: 100%;
            }

            .cf:before, .cf:after {
                content: " ";
                display: table;
            }

            .cf:after {
                clear: both;
            }

            .cf {
                *zoom: 1;
            }

            .wrap {
                width: 75%;
                max-width: 960px;
                margin: 0 auto;
                padding: 5% 0;
                margin-bottom: 5em;
            }

            .projTitle {
                font-family: "Montserrat", sans-serif;
                font-weight: bold;
                text-align: center;
                font-size: 2em;
                padding: 1em 0;
                border-bottom: 1px solid #dadada;
                letter-spacing: 3px;
                text-transform: uppercase;
            }
            .projTitle span {
                font-family: "Droid Serif", serif;
                font-weight: normal;
                font-style: italic;
                text-transform: lowercase;
                color: #777;
            }

            .heading {
                padding: 1em 0;
                border-bottom: 1px solid #D0D0D0;
            }
            .heading h1 {
                font-family: "Droid Serif", serif;
                font-size: 2em;
                float: left;
            }
            .heading a.continue:link, .heading a.continue:visited {
                text-decoration: none;
                font-family: "Montserrat", sans-serif;
                letter-spacing: -.015em;
                font-size: .75em;
                padding: 1em;
                color: #fff;
                background: #82ca9c;
                font-weight: bold;
                border-radius: 50px;
                float: right;
                text-align: right;
                -webkit-transition: all 0.25s linear;
                -moz-transition: all 0.25s linear;
                -ms-transition: all 0.25s linear;
                -o-transition: all 0.25s linear;
                transition: all 0.25s linear;
            }
            .heading a.continue:after {
                content: "\276f";
                padding: .5em;
                position: relative;
                right: 0;
                -webkit-transition: all 0.15s linear;
                -moz-transition: all 0.15s linear;
                -ms-transition: all 0.15s linear;
                -o-transition: all 0.15s linear;
                transition: all 0.15s linear;
            }
            .heading a.continue:hover, .heading a.continue:focus, .heading a.continue:active {
                background: #f69679;
            }
            .heading a.continue:hover:after, .heading a.continue:focus:after, .heading a.continue:active:after {
                right: -10px;
            }

            .tableHead {
                display: table;
                width: 100%;
                font-family: "Montserrat", sans-serif;
                font-size: .75em;
            }
            .tableHead li {
                display: table-cell;
                padding: 1em 0;
                text-align: center;
            }
            .tableHead li.prodHeader {
                text-align: left;
            }

            .cart {
                padding: 1em 0;
            }
            .cart .items {
                display: block;
                width: 100%;
                /* vertical-align: middle; */
                padding: 1.5em;
                border-bottom: 1px solid #fafafa;
            }
            .cart .items.even {
                background: #fafafa;
            }
            .cart .items .infoWrap {
                display: table;
                width: 100%;
            }
            .cart .items .cartSection {
                display: contents;
                vertical-align: middle;
            }
            .cart .items .cartSection .itemNumber {
                font-size: .75em;
                color: #777;
                margin-bottom: .5em;
            }
            .cart .items .cartSection h3 {
                font-size: 1em;
                font-family: "Montserrat", sans-serif;
                font-weight: bold;
                text-transform: uppercase;
                letter-spacing: .025em;
            }
            .cart .items .cartSection p {
                display: inline-block;
                font-size: .85em;
                color: #777777;
                font-family: "Montserrat", sans-serif;
            }
            .cart .items .cartSection p .quantity {
                font-weight: bold;
                color: #333;
            }
            .cart .items .cartSection p.stockStatus {
                color: #82CA9C;
                font-weight: bold;
                padding: .5em 0 0 1em;
                text-transform: uppercase;
            }
            .cart .items .cartSection p.stockStatus.out {
                color: #F69679;
            }
            .cart .items .cartSection .itemImg {
                width: 4em;
                float: left;
            }
            .cart .items .cartSection.qtyWrap, .cart .items .cartSection.prodTotal {
                text-align: center;
            }
            .cart .items .cartSection.qtyWrap p, .cart .items .cartSection.prodTotal p {
                font-weight: bold;
                font-size: 1.25em;
            }
            .cart .items .cartSection input.qty {
                width: 2em;
                text-align: center;
                font-size: 1em;
                padding: .25em;
                margin: 1em .5em 0 0;
            }
            .cart .items .cartSection .itemImg {
                width: 8em;
                display: inline;
                padding-right: 1em;
            }

            .special {
                display: block;
                font-family: "Montserrat", sans-serif;
            }
            .special .specialContent {
                padding: 1em 1em 0;
                display: block;
                margin-top: .5em;
                border-top: 1px solid #dadada;
            }
            .special .specialContent:before {
                content: "\21b3";
                font-size: 1.5em;
                margin-right: 1em;
                color: #6f6f6f;
                font-family: helvetica, arial, sans-serif;
            }

            a.remove {
                text-decoration: none;
                font-family: "Montserrat", sans-serif;
                color: #ffffff;
                font-weight: bold;
                background: #e0e0e0;
                padding: .5em;
                font-size: .75em;
                display: inline-block;
                border-radius: 100%;
                line-height: .85;
                -webkit-transition: all 0.25s linear;
                -moz-transition: all 0.25s linear;
                -ms-transition: all 0.25s linear;
                -o-transition: all 0.25s linear;
                transition: all 0.25s linear;
            }
            a.remove:hover {
                background: #f30;
            }

            .promoCode {
                border: 2px solid #efefef;
                float: left;
                width: 35%;
                padding: 2%;
            }
            .promoCode label {
                display: block;
                width: 100%;
                font-style: italic;
                font-size: 1.15em;
                margin-bottom: .5em;
                letter-spacing: -.025em;
            }
            .promoCode input {
                width: 85%;
                font-size: 1em;
                padding: .5em;
                float: left;
                border: 1px solid #dadada;
            }
            .promoCode input:active, .promoCode input:focus {
                outline: 0;
            }
            .promoCode a.btn {
                float: left;
                width: 15%;
                padding: .75em 0;
                border-radius: 0 1em 1em 0;
                text-align: center;
                border: 1px solid #82ca9c;
            }
            .promoCode a.btn:hover {
                border: 1px solid #f69679;
                background: #f69679;
            }

            .btn:link, .btn:visited {
                text-decoration: none;
                font-family: "Montserrat", sans-serif;
                letter-spacing: -.015em;
                font-size: 1em;
                padding: 1em 3em;
                color: #fff;
                background: #82ca9c;
                font-weight: bold;
                border-radius: 50px;
                float: right;
                text-align: right;
                -webkit-transition: all 0.25s linear;
                -moz-transition: all 0.25s linear;
                -ms-transition: all 0.25s linear;
                -o-transition: all 0.25s linear;
                transition: all 0.25s linear;
            }
            .btn:after {
                content: "\276f";
                padding: .5em;
                position: relative;
                right: 0;
                -webkit-transition: all 0.15s linear;
                -moz-transition: all 0.15s linear;
                -ms-transition: all 0.15s linear;
                -o-transition: all 0.15s linear;
                transition: all 0.15s linear;
            }
            .btn:hover, .btn:focus, .btn:active {
                background: #f69679;
            }
            .btn:hover:after, .btn:focus:after, .btn:active:after {
                right: -10px;
            }
            .promoCode .btn {
                font-size: .85em;
                padding: .5em 2em;
            }

            /* TOTAL AND CHECKOUT  */
            .subtotal {
                float: right;
                width: 35%;
            }
            .subtotal .totalRow {
                padding: .5em;
                text-align: right;
            }
            .subtotal .totalRow.final {
                font-size: 1.25em;
                font-weight: bold;
            }
            .subtotal .totalRow span {
                display: inline-block;
                padding: 0 0 0 1em;
                text-align: right;
            }
            .subtotal .totalRow .label {
                font-family: "Montserrat", sans-serif;
                font-size: .85em;
                text-transform: uppercase;
                color: #777;
            }
            .subtotal .totalRow .value {
                letter-spacing: -.025em;
                width: 35%;
            }

            @media only screen and (max-width: 39.375em) {
                .wrap {
                    width: 98%;
                    padding: 2% 0;
                }

                .projTitle {
                    font-size: 1.5em;
                    padding: 10% 5%;
                }

                .heading {
                    padding: 1em;
                    font-size: 90%;
                }

                .cart .items .cartSection {
                    width: 90%;
                    display: block;
                    float: left;
                }
                .cart .items .cartSection.qtyWrap {
                    width: 10%;
                    text-align: center;
                    padding: .5em 0;
                    float: right;
                }
                .cart .items .cartSection.qtyWrap:before {
                    content: "QTY";
                    display: block;
                    font-family: "Montserrat", sans-serif;
                    padding: .25em;
                    font-size: .75em;
                }
                .cart .items .cartSection.prodTotal, .cart .items .cartSection.removeWrap {
                    display: none;
                }
                .cart .items .cartSection .itemImg {
                    width: 25%;
                }

                .promoCode, .subtotal {
                    width: 100%;
                }

                a.btn.continue {
                    width: 100%;
                    text-align: center;
                }
            }
        </style>

        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="css/style.css?id=1234">
        <link rel="apple-touch-icon" sizes="180x180" href="./favicon_io/apple-touch-icon.png">
        <link rel="icon" type="image/png" sizes="32x32" href="./favicon_io/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="16x16" href="./favicon_io/favicon-16x16.png">
        <link rel="manifest" href="./favicon_io/site.webmanifest">


    </head>
    <body>
        <!-- header section starts -->

        <jsp:include page="header.jsp"/>

        <!-- header section ends -->

        <!-- partial:index.partial.html -->
        <div class="wrap cf">
            <div class="heading cf">
                <h1>My Cart</h1>
                <a href="Category" class="continue">Continue Shopping</a>
            </div>
            <div class="cart">
                <ul class="cartWrap">
                    <c:forEach items="${requestScope.list}" var="ct">
                        <li class="items even">
                            <div class="infoWrap"> 
                                <div class="cartSection">
                                    <img src="${ct.getBookId().getImage()}" alt="" class="itemImg" />
                                    <h3>${ct.getBookId().getName()}</h3>

                                    <div class="quantity">
                                        <input name="quantity" type="number" class="quantity-input" 
                                               value="${ct.getQuantity()}" min="1" max="${ct.getBookId().getQuantity()}"
                                                  onchange="validateMaxQuantity(this)">
                                        <button class="quantity-btn" onclick="increaseQuantity()">+</button>
                                        <button class="quantity-btn" onclick="decreaseQuantity()">-</button>

                                        <c:choose>
                                            <c:when test="${sessionScope.account ne null}">
                                                <button class="btn" onclick="addToCart('${ct.getBookId().getId()}')">add </button>
                                            </c:when> 
                                            <c:otherwise>
                                                <button class="btn" onclick="addToCartck('${ct.getBookId().getId()}')">add </button>
                                            </c:otherwise>    
                                        </c:choose>
                                    </div>
                                    <p class="stockStatus">In Stock</p>
                                </div>      
                                <div class="prodTotal cartSection">
<!--                                    <p class="subtotal">Total: $<span>${ct.getQuantity() * ct.getBookId().getPrice()}</span></p>-->
                                    <p class="subtotal">Total: $<span><fmt:formatNumber value="${ct.quantity * ct.bookId.price}" pattern="#,##0.00"/></span></p>

                                </div>
                                <div class="cartSection removeWrap">
                                    <a href="#" class="remove">x</a>
                                </div>
                            </div>
                        </li>
                    </c:forEach> 
                </ul>
            </div>

            <div class="subtotal cf">
                <ul>
                    <li class="totalRow final"><span class="label">Total</span><span class="total-value">${requestScope.total}</span></li>
                    <li class="totalRow"><a href="checkout?total=${requestScope.total}" class="btn continue">Checkout</a></li>
                </ul>
            </div>
        </div> 

        <!-- bottom navbar  -->

        <jsp:include page="footer.jsp"/>


        <!-- partial -->
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script><script  src="./js/cart.js"></script>
        <script>

                                                    function addToCart(id) {
                                                        var addButton = event.target; // Lấy đối tượng button được nhấp
                                                        var quantityInput = addButton.parentNode.querySelector('.quantity-input'); // Tìm quantity-input trong cùng một parent node
                                                        var quantity = quantityInput.value;
                                                        // Ngăn chặn hành động mặc định của nút button (không gửi yêu cầu form)
                                                        event.preventDefault();

                                                        // Sử dụng let hoặc const để khai báo biến

                                                        fetch(`Cart4Acc?num=` + quantity + `&id=` + id, {// Sử dụng giá trị của quantity và id trong URL
                                                            method: "POST"
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
                                                        var addButton = event.target; // Lấy đối tượng button được nhấp
                                                        var quantityInput = addButton.parentNode.querySelector('.quantity-input');
                                                        var quantity = quantityInput.value;
                                                        // Ngăn chặn hành động mặc định của nút button (không gửi yêu cầu form)
                                                        event.preventDefault();

                                                        // Sử dụng let hoặc const để khai báo biến

                                                        fetch(`CartDetails?num=` + quantity + `&id=` + id, {// Sử dụng giá trị của quantity và id trong URL
                                                            method: "POST"
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
                                                        var addButton = event.target; // Lấy đối tượng button được nhấp
                                                        var quantityInput = addButton.parentNode.querySelector('.quantity-input');
                                                        var currentQuantity = parseInt(quantityInput.value);
                                                        var maxQuantity = parseInt(quantityInput.max);

                                                        if (currentQuantity < maxQuantity) {
                                                            quantityInput.value = currentQuantity + 1;
                                                        }
                                                    }

                                                    function decreaseQuantity() {
                                                        var addButton = event.target; // Lấy đối tượng button được nhấp
                                                        var quantityInput = addButton.parentNode.querySelector('.quantity-input');
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
    </body>
</html>
