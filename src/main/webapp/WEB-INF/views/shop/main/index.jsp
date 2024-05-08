<%@ page import="com.nhnacademy.shoppingmall.model.product.domain.Product" %>
<%@ page import="com.nhnacademy.shoppingmall.common.page.Page" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% Page<Product> productPage = (Page<Product>) request.getAttribute("productPage"); %>

<!doctype html>
<html lang="ko">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <title>nhn아카데미 shopping mall</title>

</head>
<body>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
    <c:forEach var="product" items="${productList}">
    <div class="col">
        <div class="card shadow-sm">
<%--            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"></rect><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>--%>
            <c:choose>
                <c:when test = "${product.productImage != null && !product.productImage.isEmpty()}">
                    <img src="${product.productImage}" class="bd-placeholder-img card-img-top" alt="Product Image" width="100%" height="225" style="object-fit: cover;"/>
                </c:when>
                <c:otherwise>
                    <img src="/resources/no-image.png" class="bd-placeholder-img card-img-top" alt="Product Image" width="100%" height="225" style="object-fit: cover;"/>
                </c:otherwise>
            </c:choose>

            <div class="card-body">
                <p class="card-text">${product.getProductName()}</p>
                <div class="d-flex justify-content-between align-items-center">
                    <div class="btn-group">
                        <button onclick="window.location.href='/detail.do?productId=${product.productId}'" type="button" class="btn btn-sm btn-outline-secondary">자세히 보기</button>
                    </div>
<%--                    <small class="text-muted">9 mins</small>--%>
                </div>
            </div>
        </div>
    </div>
    </c:forEach>
</div>

<br><br>
<div class="pagination justify-content-center">
    <%
        String categoryId = request.getParameter("categoryId");

        int currentPage = 1;
        int totalPages = 1;

        if (productPage != null) {
            totalPages = (int) Math.ceil((double) productPage.getTotalCount() / 10);  // 총 페이지 수 계산
            currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;  // 현재 페이지
        }

        String link;
        String previousLink = "?page=" + (currentPage - 1) + (categoryId != null ? "&categoryId=" + categoryId : "");
        String nextLink = "?page=" + (currentPage + 1) + (categoryId != null ? "&categoryId=" + categoryId : "");

        for (int i = 1; i <= totalPages; i++) {
            boolean isActive = (i == currentPage);

            if (categoryId != null) {
                link = "?page=" + i + "&categoryId=" + categoryId;
            } else {
                link = "?page=" + i;
            }
    %>

    <nav aria-label="Page navigation example">
        <ul class="pagination">
<%--            <% if (currentPage > 1) { %>--%>
<%--            <li class="page-item">--%>
<%--                <a class="page-link" href="<%= previousLink %>" aria-label="Previous">--%>
<%--                    <span aria-hidden="true">&laquo;</span> 이전--%>
<%--                </a>--%>
<%--            </li>--%>
<%--            <% } %>--%>


            <li class="page-item <%= isActive ? "active" : "" %>">
                <a class="page-link" href="<%= link %>"><%= i %></a>
            </li>
            <% } %>

<%--            <% if (currentPage < totalPages) { %>--%>
<%--            <li class="page-item">--%>
<%--                <a class="page-link" href="<%= nextLink %>" aria-label="Next">--%>
<%--                    다음 <span aria-hidden="true">&raquo;</span>--%>
<%--                </a>--%>
<%--            </li>--%>
<%--            <% } %>--%>
        </ul>
    </nav>
</div>

<%--최근 본 상품--%>
<c:if test="${recentlyProduct != null && !recentlyProduct.isEmpty()}">
<section class="py-5 bg-light">
    <div class="container px-4 px-lg-5 mt-5">
        <h2 class="fw-bolder mb-4">최근 본 상품</h2>
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
            <c:forEach var="recently" items="${recentlyProduct}">
                <div class="col mb-5">
                    <div class="card h-100">
                        <!-- Product image-->
                        <img class="card-img-top" src="${recently.productImage}" alt="..." />
                        <!-- Product details-->
                        <div class="card-body p-4">
                            <div class="text-center">
                                <!-- Product name-->
                                <h5 class="fw-bolder">${recently.productName}</h5>
                                <!-- Product reviews-->
                                <div class="d-flex justify-content-center small text-warning mb-2">
                                    <div class="bi-star-fill"></div>
                                    <div class="bi-star-fill"></div>
                                    <div class="bi-star-fill"></div>
                                    <div class="bi-star-fill"></div>
                                    <div class="bi-star-fill"></div>
                                </div>
                                <!-- Product price-->
                                ${recently.productSalePrice}
                            </div>
                        </div>
                        <!-- Product actions-->
                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                            <form method="post" action="cartAction.do">
                                <div hidden class="form-floating mb-3">
                                    <input type="text" class="form-control" name="productId" id="productId" placeholder="상품 아이디" value="${recently.productId}" required>
                                    <label for="productId">장바구니 상품 아이디</label>
                                </div>
                                <input class="form-control text-center me-3" id="cartQuantity" name="cartQuantity" type="number" value="1" style="max-width: 3rem; align-items: center" />
                                <button class="btn btn-outline-dark mt-auto" type="submit">장바구니 추가</button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>
</c:if>

<c:if test="${recentlyProduct == null || recentlyProduct.isEmpty()}">
    <p>최근 본 상품이 없습니다.</p>
</c:if>

</body>
</html>
