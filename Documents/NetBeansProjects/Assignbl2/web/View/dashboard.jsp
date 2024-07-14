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
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <style>
            canvas {
                -moz-user-select: none;
                -webkit-user-select: none;
                -ms-user-select: none;
            }
        </style>
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
                    <div>
                        <canvas id="myChart"></canvas>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page='footer.jsp'/>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var ctx = document.getElementById('myChart').getContext('2d');


                var chart = new Chart(ctx, {
                    type: 'line',
                    data: {

                        labels: ['22/4', '23/4', '24/4', '25/4'],
                        datasets: [
                            {
                                label: 'History',
                                data: [<c:forEach items="${requestScope.list1}" var="ct">${ct.getData()},
            </c:forEach>],
                                borderColor: 'red',
                                backgroundColor: 'rgba(255, 0, 0, 0.1)',
                                borderWidth: 1,
                                fill: false,
                                tension: 0.4,
                                hidden: true
                            },
                            {
                                label: 'Science-Technology',
                                data: [<c:forEach items="${requestScope.list4}" var="ct">${ct.getData()},
            </c:forEach>],
                                borderColor: 'green',
                                backgroundColor: 'rgba(255, 0, 0, 0.1)',
                                borderWidth: 1,
                                fill: false,
                                tension: 0.4,
                                hidden: true
                            },
                            {
                                label: 'Self Development',
                                data: [<c:forEach items="${requestScope.list3}" var="ct">${ct.getData()},
            </c:forEach>],
                                borderColor: 'blue',
                                backgroundColor: 'rgba(255, 0, 0, 0.1)',
                                borderWidth: 1,
                                fill: false,
                                tension: 0.4,
                                hidden: true
                            },
                            {
                                label: 'Novel',
                                data: [<c:forEach items="${requestScope.list10}" var="ct">${ct.getData()},
            </c:forEach>],
                                borderColor: 'yellow',
                                backgroundColor: 'rgba(255, 0, 0, 0.1)',
                                borderWidth: 1,
                                fill: false,
                                tension: 0.4,
                                hidden: true
                            },
                            {
                                label: 'Comics & Manga',
                                data: [<c:forEach items="${requestScope.list8}" var="ct">${ct.getData()},
            </c:forEach>],
                                borderColor: 'orange',
                                backgroundColor: 'rgba(255, 0, 0, 0.1)',
                                borderWidth: 1,
                                fill: false,
                                tension: 0.4,
                                hidden: true
                            },
                            {
                                label: 'Kids',
                                data: [<c:forEach items="${requestScope.list11}" var="ct">${ct.getData()},
            </c:forEach>],
                                borderColor: 'Gray',
                                backgroundColor: 'rgba(255, 0, 0, 0.1)',
                                borderWidth: 1,
                                fill: false,
                                tension: 0.4,
                                hidden: true
                            },
                            {
                                label: 'Total',
                                data: [<c:forEach items="${requestScope.total}" var="ct">${ct.getData()},
            </c:forEach>],
                                borderColor: 'purple',
                                backgroundColor: 'rgba(255, 0, 0, 0.1)',
                                borderWidth: 1,
                                fill: false,
                                tension: 0.4,
                                hidden: true
                            }
                        ]
                    },
                    options: {
                        scales: {
                            y: {
                                title: {
                                    display: true,
                                    text: 'Doanh thu'
                                }
                            },
                            x: {
                                title: {
                                    display: true,

                                }
                            }
                        }
                    }
                });

                document.getElementById('select-data').addEventListener('change', function () {
                    var selectedValue = this.value;

                    chart.data.datasets.forEach(function (dataset) {
                        if (dataset.label === selectedValue || selectedValue === 'total') {
                            dataset.hidden = false;
                        } else {
                            dataset.hidden = true;
                        }
                    });

                    chart.update();
                });
            });
        </script>
    </body>


</html>
