<%@ page import="com.nhnacademy.shoppingmall.model.product.domain.Product" %>
<%@ page import="com.nhnacademy.shoppingmall.common.page.Page" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <title>주문 내역</title>

</head>
<body>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table class="table">
    <thead>
        <tr>
            <th scope="col">주문 내역</th>
            <th scope="col">주문 날짜</th>
            <th scope="col">총 가격</th>
            <th scope="col">주문 상태</th>
            <th scope="col">받는 사람</th>
            <th scope="col">받는 사람 주소</th>
            <th scope="col">받는 사람 상세 주소</th>
            <th scope="col">받는 사람 폰번호</th>
            <th scope="col">상세 정보</th>
        </tr>
    </thead>


    <tbody class="table-group-divider">
        <c:forEach var="order" items="${orderList}" varStatus="status">
            <tr>
                <th scope="row">${status.index + 1}</th>
                <td>${order.orderDate}</td>
                <td>${order.orderTotalPrice}</td>
                <td>${order.orderStatus}</td>
                <td>${order.orderName}</td>
                <td>${order.orderAddress}</td>
                <td>${order.orderDetailAddress}</td>
                <td>${order.orderPhoneNumber}</td>
                <td><button type="button" class="btn btn-primary" onclick="window.location.href='/admin/mainDetail.do?orderId=${order.orderId}'" >상세 정보</button></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
