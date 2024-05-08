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
                            <button onclick="window.location.href='updateForm.do?productId=${product.productId}'" type="button" class="btn btn-sm btn-outline-secondary">제품 수정</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>


<div class="pagination justify-content-center">
    <%
        int currentPage = 1;
        int totalPages = 1;

        if (productPage != null) {
            totalPages = (int) Math.ceil((double) productPage.getTotalCount() / 10);  // 총 페이지 수 계산
            currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;  // 현재 페이지
        }
    %>

    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <% if (currentPage > 1) { %>
            <li class="page-item">
                <a class="page-link" href="?page=<%= currentPage - 1 %>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span> 이전
                </a>
            </li>
            <% } %>

            <%
                for (int i = 1; i <= totalPages; i++) {
                    boolean isActive = (i == currentPage);
            %>
            <li class="page-item <%= isActive ? "active" : "" %>">
                <a class="page-link" href="?page=<%= i %>"><%= i %></a>
            </li>
            <% } %>

            <% if (currentPage < totalPages) { %>
            <li class="page-item">
                <a class="page-link" href="?page=<%= currentPage + 1 %>" aria-label="Next">
                    다음 <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
            <% } %>
        </ul>
    </nav>
</div>

</body>
</html>
