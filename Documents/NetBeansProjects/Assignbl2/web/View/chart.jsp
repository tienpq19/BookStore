<%-- 
    Document   : chart
    Created on : Apr 24, 2024, 10:16:15 PM
    Author     : TienP
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
    </head>
    <body>
        <div>
            <label for="select-data">Chọn dữ liệu:</label>
            <select id="select-data">
                <option value="total">Tổng cộng</option>
                <option value="history">Sách lịch sử</option>
                <option value="science">Sách khoa học</option>
                <option value="novel">Tiểu thuyết</option>
            </select>
        </div>

        
        

        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var ctx = document.getElementById('myChart').getContext('2d');


                var chart = new Chart(ctx, {
                    type: 'line',
                    data: {

                        labels: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4'],
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
                                data: [<c:forEach items="${requestScope.list2}" var="ct">${ct.getData()},
            </c:forEach>],
                                borderColor: 'red',
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
                                borderColor: 'red',
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
                                borderColor: 'red',
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
                                borderColor: 'red',
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
                                borderColor: 'red',
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
                                borderColor: 'red',
                                backgroundColor: 'rgba(255, 0, 0, 0.1)',
                                borderWidth: 1,
                                fill: false,
                                tension: 0.4,
                                hidden: true
                            },
                                    
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
