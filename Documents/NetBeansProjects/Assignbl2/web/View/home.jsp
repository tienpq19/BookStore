<%-- 
    Document   : home
    Created on : Mar 15, 2024, 5:13:50 AM
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
        <link rel="stylesheet" href="css/style.css">

        <link rel="apple-touch-icon" sizes="180x180" href="favicon_io/apple-touch-icon.png">
        <link rel="icon" type="image/png" sizes="32x32" href="favicon_io/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="16x16" href="favicon_io/favicon-16x16.png">
        <link rel="manifest" href="favicon_io/site.webmanifest">
    </head>
    <body>

        <!-- header section starts  -->

        <header class="header">

            <div class="header-1">

                <a href="#" class="logo"> <i class="fas fa-book"></i> Books4Nerds </a>

                <form action="Category" method ="post" class="search-form">
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
                    <a href="#featured">featured</a>
                     <a href="Category">Category</a> 
                    <a href="#reviews">reviews</a>
                    <a href="View./feedback.jsp">feedback</a>

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

        <!-- header section ends -->

        <!-- bottom navbar  -->

        <nav class="bottom-navbar">
            <a href="#home" class="fas fa-home"></a>
            <a href="#featured" class="fas fa-list"></a>
            <a href="#arrivals" class="fas fa-tags"></a>
            <a href="#reviews" class="fas fa-comments"></a>
            <a href="#feedback" class="fas fa-feedback"></a>
        </nav>

        <!-- login form  -->



        <!-- home section starts  -->

        <section class="home" id="home">

            <div class="row">

                <div class="content">
                    <h3>Up to 75% off</h3>
                    <p>If you’re an Engineering student and need a books, Books4MU has great deals on a wide range of books. Shop for these books from top authors and avail hugely discounted prices</p>
                    <a href="#" class="btn">shop now</a>
                </div>

                <div class="swiper books-slider">
                    <div class="swiper-wrapper">
                        <c:forEach items="${requestScope.booksbyCate}" var="bk2">
                            <a href="#" class="swiper-slide"><img src="${bk2.getImage()}" alt=""></a>

                        </c:forEach>
                    </div>
                    <img src="image/stand.png" class="stand" alt="">
                </div>

            </div>

        </section>

        <!-- home section ense  -->

        <!-- icons section starts  -->

        <section class="icons-container">

            <div class="icons">
                <i class="fas fa-shipping-fast"></i>
                <div class="content">
                    <h3>free shipping</h3>
                    <p>order over $100</p>
                </div>
            </div>

            <div class="icons">
                <i class="fas fa-lock"></i>
                <div class="content">
                    <h3>secure payment</h3>
                    <p>100 secure payment</p>
                </div>
            </div>

            <div class="icons">
                <i class="fas fa-redo-alt"></i>
                <div class="content">
                    <h3>easy returns</h3>
                    <p>10 days returns</p>
                </div>
            </div>

            <div class="icons">
                <i class="fas fa-headset"></i>
                <div class="content">
                    <h3>24/7 support</h3>
                    <p>call us anytime</p>
                </div>
            </div>

        </section>

        <!-- icons section ends -->

        <!-- featured section starts  -->

        <section class="featured" id="featured">

            <h1 class="heading"> <span>Hot books</span> </h1>

            <div class="swiper featured-slider">

                <div class="swiper-wrapper">
                    <c:forEach items="${requestScope.booksTop}" var="bt">
                        <div class="swiper-slide box">
                            <div class="icons">
                                <a href="#" class="fas fa-search"></a>
                                <a href="#" class="fas fa-eye"></a>
                            </div>
                            <div class="image">
                                <a href="details?id=${bt.getId()}"> <img src="${bt.getImage()}" alt=""> </a>
                            </div>
                            <div class="content">
                                <h3>${bt.getName()}</h3>
                                <div class="price">$${bt.getPrice()} <span>$${bt.getPrice() + 5}</span></div>
                                <button class="btn" onclick="addToCart('${bt.getId()}')">add to cart</button>
                            </div>
                        </div>

                    </c:forEach>

                </div>

                <div class="swiper-button-next"></div>
                <div class="swiper-button-prev"></div>

            </div>

        </section>

        <!-- featured section ends -->

        <!-- newsletter section starts -->

        <section class="newsletter">

            <form action="">
                <h3>subscribe for latest updates</h3>
                <input type="email" name="" placeholder="enter your email" id="" class="box">
                <input type="submit" value="subscribe" class="btn">
            </form>

        </section>

        <!-- newsletter section ends -->

        <!-- category section starts  -->

        <section class="arrivals" id="arrivals">

            <h1 class="heading"> <span>Category</span> </h1>

            <div class="swiper arrivals-slider">

                <div class="swiper-wrapper">
                    <c:forEach items="${requestScope.books1byCate}" var="b1c">
                        <a href="#" class="swiper-slide box">
                            <div class="image">
                                <img src="${b1c.getImage()}" alt="">
                            </div>
                            <div class="content">
                                <h3>${b1c.getCategory().getNameCate()}</h3>
                            </div>
                        </a>
                    </c:forEach>
                </div>

            </div>

            <div class="swiper arrivals-slider">

                <div class="swiper-wrapper">
                    <c:forEach items="${requestScope.books1byCate2}" var="b1c">
                        <a href="#" class="swiper-slide box">
                            <div class="image">
                                <img src="${b1c.getImage()}" alt="">
                            </div>
                            <div class="content">
                                <h3>${b1c.getCategory().getNameCate()}</h3>
                            </div>
                        </a>
                    </c:forEach>
                </div>

            </div>

        </section>

        <!-- category section ends -->

        <!-- deal section starts  -->

        <section class="deal">

            <div class="content">
                <h3>deal of the day</h3>
                <h1>up to 50% off</h1>
                <p>Checkout before this deal expires at midnight.</p>
                <a href="#" class="btn">shop now</a>
            </div>

            <div class="image">
                <img src="image/deal-img.jpg" alt="">
            </div>

        </section>

        <!-- deal section ends -->

        <!-- reviews section starts  -->

        <section class="reviews" id="reviews">

            <h1 class="heading"> <span>client's reviews</span> </h1>

            <div class="swiper reviews-slider">

                <div class="swiper-wrapper">

                    <div class="swiper-slide box">
                        <img src="image/pic-1.png" alt="">
                        <h3>ujjwal </h3>
                        <p>First of all it customer service is excellent. We get all author book for Mumbai University. People should try here affordable and best price.</p>
                        <div class="stars">
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star-half-alt"></i>
                        </div>
                    </div>

                    <div class="swiper-slide box">
                        <img src="image/pic-2.png" alt="">
                        <h3>marry </h3>
                        <p>Best book store almost all books are available for prepartion of exam related or other books are available on reaonable price also.</p>
                        <div class="stars">
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star-half-alt"></i>
                        </div>
                    </div>

                    <div class="swiper-slide box">
                        <img src="image/pic-3.png" alt="">
                        <h3>Raghu </h3>
                        <p>Customer Service is good. Greetings to customer and making the required Books available to Customers is very good.</p>
                        <div class="stars">
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star-half-alt"></i>
                        </div>
                    </div>
                    <div class="swiper-slide box">
                        <img src="image/pic-4.png" alt="">
                        <h3>Pooja </h3>
                        <p>This book centre have large amount of a books. The engineering study material all semester books are available.then the peacefull and nice book centre.</p>
                        <div class="stars">
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star-half-alt"></i>
                        </div>
                    </div>

                    <div class="swiper-slide box">
                        <img src="image/pic-5.png" alt="">
                        <h3>Abhinav </h3>
                        <p>I migrated to the online platform on Just books because I was finding it difficult to go to their libraries before closing time.</p>
                        <div class="stars">
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star-half-alt"></i>
                        </div>
                    </div>

                    <div class="swiper-slide box">
                        <img src="image/pic-6.png" alt="">
                        <h3>Sidddhi </h3>
                        <p>I love the product because it is very easy to find. The book are in really organized manner you can easily find the book you want.</p>
                        <div class="stars">
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star-half-alt"></i>
                        </div>
                    </div>

                </div>

            </div>

        </section>

        <!-- reviews section ends -->

        <!-- feedback section starts  -->

        <section class="blogs" id="blogs">

            <h1 class="heading"> <span>feedback</span> </h1>

            <section class="newsletter">

                <form action="">
                    <h3>give your feedback here...</h3>
                    <a href="./feedback.html" class="btn">Feedback</a>
                    <!-- <a href="./feedback.html"><input type="submit" value="feedback"> -->
                    </a>
                </form>

            </section>
        </section>


        <jsp:include page="footer.jsp"/>


        <!-- Ví dụ về nút Add to Cart -->


        <script>
            function addToCart(id) {
                // Ngăn chặn hành động mặc định của nút button (không gửi yêu cầu form)
                event.preventDefault();

                // Sử dụng let hoặc const để khai báo biến

                fetch(`Cart?num=1&id=` + id, {// Sử dụng giá trị của cid trong URL
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
        </script>  




        <script src="https://unpkg.com/swiper@7/swiper-bundle.min.js"></script>

        <!-- custom js file link  -->
        <script src="js/script.js"></script>
        <script src="https://kit.fontawesome.com/54e6ef273a.js" crossorigin="anonymous"></script>
    </body>
</html>

