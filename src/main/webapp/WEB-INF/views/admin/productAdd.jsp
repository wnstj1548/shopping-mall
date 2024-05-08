<%@ page import="java.util.List" %>
<%@ page import="com.nhnacademy.shoppingmall.model.category.domain.Category" %>
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
            <c:url var="action" value="addAction.do"/>
            <c:set var="buttonText" value="등록"/>
        </c:when>
        <c:otherwise>
            <c:url var="action" value="updateAction.do"/>
            <c:set var="buttonText" value="수정"/>
        </c:otherwise>
    </c:choose>

    <c:url var="deleteUrl" value="deleteAction.do">
        <c:param name="productId" value="${product.productId}" />
    </c:url>

        <form method="post" action="${action}" enctype="multipart/form-data">

            <c:if test="${!empty product}">
                <div hidden class="form-floating mb-3">
                    <input type="text" class="form-control" name="productId" id="productId" placeholder="상품 아이디" value="${product.productId}" required>
                    <label for="productId">상품 아이디</label>
                </div>
            </c:if>

            <div class="form-floating mb-3">
                <input type="text" class="form-control" name="productName" id="productName" placeholder="상품 이름" value="${product.productName}" required>
                <label for="productName">상품 이름</label>
            </div>

            <div class="form-floating mb-3">
                <input type="number" min="0" class="form-control" name="productQuantity" id="productQuantity" placeholder="재고" value="${product.productQuantity}"  required>
                <label for="productQuantity">재고</label>
            </div>
            <p>상품 이미지</p>
            <div class="input-group mb-3">
                <input type="file" class="form-control" name="productImage" id="productImage" required>
                <label class="input-group-text" for="productImage">선택</label>
            </div>
            <p>상품 상세 이미지</p>
            <div class="input-group mb-3">
                <input type="file" class="form-control" name="productDetailImage" id="productDetailImage" required>
                <label class="input-group-text" for="productDetailImage">선택</label>
            </div>
            <div class="form-floating mb-3">
                <input type="number" min="0" class="form-control" name="productOriginalPrice" id="productOriginalPrice" placeholder="정가" value="${product.productOriginalPrice}"  required>
                <label for="productOriginalPrice">정가</label>
            </div>
            <div class="form-floating mb-3">
                <input type="number" min="0" class="form-control" name="productSalePrice" id="productSalePrice" placeholder="판매가" value="${product.productSalePrice}"  required>
                <label for="productSalePrice">판매가</label>
            </div>
            <div class="form-floating">
                <textarea class="form-control" placeholder="Leave a comment here" name ="productContent" id="productContent" style="height: 100px" required>${product.productContent}</textarea>
                <label for="productContent">상품 설명</label>
            </div>
            <div class="form-floating">
                <select class="form-select" id="category1" name="category1" aria-label="Floating label select example" required>
                    <option value="null" selected>카테고리를 선택해주세요.(필수)</option>
                    <c:forEach var="category" items="${categoryList}">
                        <option value="${category.getCategoryId()}">${category.getCategoryName()}</option>
                    </c:forEach>
                </select>
                <label for="category1">카테고리</label>
            </div>
            <div class="form-floating">
                <select class="form-select" id="category2" name="category2" aria-label="Floating label select example">
                    <option value="null" selected>카테고리를 선택해주세요.(선택)</option>
                    <c:forEach var="category" items="${categoryList}">
                        <option value="${category.getCategoryId()}">${category.getCategoryName()}</option>
                    </c:forEach>
                </select>
                <label for="category2">카테고리</label>
            </div>
            <div class="form-floating">
                <select class="form-select" id="category3" name="category3" aria-label="Floating label select example">
                    <option value="null" selected>카테고리를 선택해주세요.(선택)</option>
                    <c:forEach var="category" items="${categoryList}">
                        <option value="${category.getCategoryId()}">${category.getCategoryName()}</option>
                    </c:forEach>
                </select>
                <label for="category3">카테고리</label>
            </div>
            <c:choose>
                <c:when test="${empty product}">
                    <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">등록</button>
                </c:when>
                <c:otherwise>
                    <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">수정</button>
                </c:otherwise>
            </c:choose>
        </form>

    <c:choose>
        <c:when test="${empty product}">
        </c:when>
        <c:otherwise>
            <form action="${deleteUrl}" method="post">
                <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">삭제</button>
            </form>
        </c:otherwise>
    </c:choose>
    </body>
</html>
