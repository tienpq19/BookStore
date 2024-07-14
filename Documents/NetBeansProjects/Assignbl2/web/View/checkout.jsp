<%-- 
    Document   : checkout
    Created on : Mar 18, 2024, 7:10:45 AM
    Author     : TienP
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            .row {
                display: -ms-flexbox; /* IE10 */
                display: flex;
                -ms-flex-wrap: wrap; /* IE10 */
                flex-wrap: wrap;
                margin: 0 -16px;
            }

            .col-25 {
                -ms-flex: 25%; /* IE10 */
                flex: 25%;
            }

            .col-50 {
                -ms-flex: 50%; /* IE10 */
                flex: 50%;
            }

            .col-75 {
                -ms-flex: 75%; /* IE10 */
                flex: 75%;
            }

            .col-25,
            .col-50,
            .col-75 {
                padding: 0 16px;
            }

            .container {
                background-color: #f2f2f2;
                padding: 5px 20px 15px 20px;
                border: 1px solid lightgrey;
                border-radius: 3px;
            }

            input[type=text] {
                width: 100%;
                margin-bottom: 20px;
                padding: 12px;
                border: 1px solid #ccc;
                border-radius: 3px;
            }

            label {
                margin-bottom: 10px;
                display: block;
            }

            .icon-container {
                margin-bottom: 20px;
                padding: 7px 0;
                font-size: 24px;
            }

            .btn {
                background-color: #04AA6D;
                color: white;
                padding: 12px;
                margin: 10px 0;
                border: none;
                width: 100%;
                border-radius: 3px;
                cursor: pointer;
                font-size: 17px;
            }

            .btn:hover {
                background-color: #45a049;
            }

            span.price {
                float: right;
                color: grey;
            }

            /* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other (and change the direction - make the "cart" column go on top) */
            @media (max-width: 800px) {
                .row {
                    flex-direction: column-reverse;
                }
                .col-25 {
                    margin-bottom: 20px;
                }
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="row">
            <div class="col-75">
                <div class="container">
                    <form action="checkout" method = post>

                        <div class="row">
                            <div class="col-50">
                                <h3>Billing Address</h3>
                                <label for="fname"><i class="fa fa-user"></i> Full Name</label>
                                <input type="text" id="fname" name="firstname" placeholder="John M. Doe">
                                <label for="email"><i class="fa fa-envelope"></i> Phone</label>
                                <input type="text" id="email" name="phone" placeholder="">
                                <label for="adr"><i class="fa fa-address-card-o"></i> Address</label>
                                <input type="text" id="adr" name="address" placeholder="542 W. 15th Street">
                                <label for="city"><i class="fa fa-institution"></i> City</label>
                                <input type="text" id="city" name="city" placeholder="New York">

                                <div class="row">
                                    <div class="col-50">
                                        <label for="state">State</label>
                                        <input type="text" id="state" name="state" placeholder="NY">
                                    </div>
                                    <div class="col-50">
                                        <label for="zip">Zip</label>
                                        <input type="text" id="zip" name="zip" placeholder="10001" required>
                                    </div>
                                    <div class="col-50">
                                        <label for="payment-method">Select Payment Method:</label>
                                        <select id="payment-method" name="payment-method" onchange="togglePaymentFields()">
                                            <option value="cash-on-delivery">Cash on Delivery</option>
                                            <option value="credit-card">Credit Card</option>
                                        </select>
                                    </div>
                                    
                                </div>
                            </div>

                            <div class="col-50" id="credit-card-fields" style="display: none;">
                                <h3>Payment Details</h3>
                                <label for="cname">Name on Card</label>
                                <input type="text" id="cname" name="cardname" placeholder="John More Doe">
                                <label for="ccnum">Credit Card Number</label>
                                <input type="text" id="ccnum" name="cardnumber" placeholder="1111-2222-3333-4444">
                                <label for="expmonth">Exp Month</label>
                                <input type="text" id="expmonth" name="expmonth" placeholder="September">
                                <div class="row">
                                    <div class="col-50">
                                        <label for="expyear">Exp Year</label>
                                        <input type="text" id="expyear" name="expyear" placeholder="2018">
                                    </div>
                                    <div class="col-50">
                                        <label for="cvv">CVV</label>
                                        <input type="text" id="cvv" name="cvv" placeholder="352">
                                    </div>
                                </div>
                            </div>

                        </div>
                        <label>
                            <input type="checkbox" checked="checked" name="sameadr"> Shipping address same as billing
                        </label>
                        <input type="submit" value="Continue to checkout" class="btn">
                    </form>
                </div>
            </div>

            <div class="col-25">
                <div class="container">
                    <h4>Cart

                    </h4>
                    <c:forEach items="${requestScope.list}" var="ct">
                        <p><a href="details?id=${ct.getBookId().getId()}">${ct.getBookId().getName()} * ${ct.getQuantity()}</a> <span class="price">${ct.getQuantity() * ct.getBookId().getPrice()}</span></p>                   
                        </c:forEach>
                    <hr>
                    <p>Total <span class="price" style="color:black"><b>$${requestScope.total}</b></span></p>

                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
<script>
    function togglePaymentFields() {
        var paymentMethod = document.getElementById("payment-method").value;
        var creditCardFields = document.getElementById("credit-card-fields");

        if (paymentMethod === "credit-card") {
            creditCardFields.style.display = "block";
        } else {
            creditCardFields.style.display = "none";
        }
    }
    function toggleZipRequirement() {
        var paymentMethod = document.getElementById("payment-method").value;
        var zipField = document.getElementById("zip");

        if (paymentMethod === "credit-card") {
            zipField.required = true;  // Yêu cầu trường zip nếu thanh toán bằng thẻ tín dụng
        } else {
            zipField.required = false; // Không yêu cầu trường zip nếu thanh toán bằng cách khác (ví dụ: thanh toán khi nhận hàng)
        }
    }

    // Gọi hàm toggleZipRequirement khi người dùng thay đổi phương thức thanh toán
    document.getElementById("payment-method").addEventListener("change", toggleZipRequirement);
</script>

