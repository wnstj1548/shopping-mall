<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <title>상품 추가</title>

</head>
<body>

<c:choose>
    <c:when test="${empty product}">
        <c:url var="action" value="cartOrderAction.do"/>
    </c:when>
    <c:otherwise>
        <c:url var="action" value="orderAction.do"/>
    </c:otherwise>
</c:choose>

<form method="post" action="${action}">

    <c:if test="${!empty product}">
        <input type="hidden" name="productId" value= ${product.productId} required>

        <div class="form-floating mb-3">
            <input type="number" min="0" class="form-control" name="productCount" id="productCount" placeholder="상품 개수" value = "1" required>
            <label for="productCount">상품 개수</label>
        </div>
    </c:if>

    <div class="form-floating mb-3">
        <input type="text" class="form-control" name="orderName" id="orderName" placeholder="받는 분 성함" required>
        <label for="orderName">받는 분 성함</label>
    </div>

    <div class="form-floating mb-3">
        <input type="number" class="form-control" name="orderZipcode" id="orderZipcode" placeholder="우편번호(06070)"  required>
        <label for="orderZipcode">우편번호(06070)</label>
    </div>

    <div class="form-floating mb-3">
        <input type="text" class="form-control" name="orderAddress" id="orderAddress" placeholder="받는 분 주소" required>
        <label for="orderAddress">받는 분 주소</label>
    </div>
    <div class="form-floating">
        <input type="text" class="form-control" placeholder="Leave a comment here" name ="orderDetailAddress" id="orderDetailAddress" required>
        <label for="orderDetailAddress">받는 분 상세주소</label>
    </div>
    <div class="form-floating">
        <input type="text" class="form-control" placeholder="Leave a comment here" name ="orderPhoneNumber" id="orderPhoneNumber" required>
        <label for="orderPhoneNumber">받는 분 전화번호(01000000000)</label>
    </div>
    <div class="form-floating">
        <input type="text" class="form-control" placeholder="Leave a comment here" name ="orderReqeust" id="orderReqeust">
        <label for="orderReqeust">배송 요청사항</label>
    </div>
    <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">등록</button>
</form>
</body>
</html>
