<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>제품 상세</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/styles.css" rel="stylesheet" />
</head>
<body>
    <section class="py-5">
        <div class="container px-4 px-lg-5 my-5">
            <div class="row gx-4 gx-lg-5 align-items-center">
                <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0" src="${product.productDetailImage}" alt="..." /></div>
                <div class="col-md-6">
                    <div class="small mb-1">${product.productId}</div>
                    <h1 class="display-5 fw-bolder">${product.productName}</h1>
                    <div class="fs-5 mb-5">
                        <span class="text-decoration-line-through">${product.productOriginalPrice}</span>
                        <span>${product.productSalePrice}</span>
                    </div>
                    <p class="lead">${product.productContent}</p>
                    <form action="cartAction.do" method="post">
                        <div class="d-flex">
                            <div hidden class="form-floating mb-3">
                                <input type="text" class="form-control" name="productId" id="productId" placeholder="상품 아이디" value="${product.productId}" required>
                                <label for="productId">장바구니 상품 아이디</label>
                            </div>
                            <input class="form-control text-center me-3" id="cartQuantity" name="cartQuantity" type="number" value="1" style="max-width: 3rem" />
                            <button class="btn btn-outline-dark flex-shrink-0" type="submit">
                                <i class="bi-cart-fill me-1"></i>
                                Add to cart
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>
