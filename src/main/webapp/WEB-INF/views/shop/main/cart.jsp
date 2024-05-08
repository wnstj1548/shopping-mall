<%@ page import="com.nhnacademy.shoppingmall.model.product.domain.Product" %>
<%@ page import="com.nhnacademy.shoppingmall.common.page.Page" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
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
    <title>장바구니</title>

</head>
<body>

<c:set var = "totalPrice" value = "0" />

<c:if test="${shoppingCart != null && cartProductList != null}">
    <section class="ftco-section ftco-cart">
        <div class="container">
            <div class="row">
                <div class="col-md-12 ftco-animate">
                    <div class="cart-list">
                        <table class="table">
                            <thead class="thead-primary">
                            <tr class="text-center">
                                <th>&nbsp;</th>
                                <th>&nbsp;</th>
                                <th>Product</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Total</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="cartProduct" items="${cartProductList}">
                                <c:url var="deleteCartUrl" value="/cartDeleteAction.do">
                                    <c:param name="cartDeleteProductId" value="${cartProduct.productId}" />
                                </c:url>
                                <tr class="text-center">
                                    <form action="${deleteCartUrl}" method="post">
                                        <td class="product-remove">
                                            <button type="submit">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="28" height="28" fill="currentColor" class="bi bi-x-square-fill" viewBox="0 0 16 16">
                                                    <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zm3.354 4.646L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708"/>
                                                </svg>
                                            </button>
                                        </td>
                                    </form>

                                    <td class="image-prod"><div class="img" style="background-image:url(${cartProduct.productImage});"></div></td>

                                    <td class="product-name">
                                        <h3>${cartProduct.productName}</h3>
                                        <p>${cartProduct.productContent}</p>
                                    </td>

                                    <td class="price">${cartProduct.productSalePrice}</td>

                                    <td class="quantity">
                                        <div class="input-group mb-3">
                                            <input type="text" name="quantity" class="quantity form-control input-number" value="${shoppingCart.get(cartProduct.productId)}" min="1" max="100">
                                        </div>
                                    </td>

                                    <td class="total">${shoppingCart.get(cartProduct.productId) * cartProduct.productSalePrice}</td>
                                    <c:set var= "totalPrice" value="${totalPrice + shoppingCart.get(cartProduct.productId) * cartProduct.productSalePrice}"/>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row justify-content-start">
                <div class="col col-lg-5 col-md-6 mt-5 cart-wrap ftco-animate">
                    <div class="cart-total mb-3">
                        <h3>Cart Totals</h3>
                        <p class="d-flex total-price">
                            <span>Total</span> &nbsp&nbsp
                            <span>${totalPrice}</span>
                        </p>
                        <a href="#" class="btn btn-primary py-3 px-4">주문하기</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
</c:if>

</body>
</html>
