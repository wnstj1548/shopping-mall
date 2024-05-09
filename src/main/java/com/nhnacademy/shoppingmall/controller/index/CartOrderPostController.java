package com.nhnacademy.shoppingmall.controller.index;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.model.order.domain.Order;
import com.nhnacademy.shoppingmall.model.order.repository.impl.OrderRepositoryImpl;
import com.nhnacademy.shoppingmall.model.order.service.OrderService;
import com.nhnacademy.shoppingmall.model.order.service.impl.OrderServiceImpl;
import com.nhnacademy.shoppingmall.model.orderDetail.domain.OrderDetail;
import com.nhnacademy.shoppingmall.model.orderDetail.repository.impl.OrderDetailRepositoryImpl;
import com.nhnacademy.shoppingmall.model.orderDetail.service.OrderDetailService;
import com.nhnacademy.shoppingmall.model.orderDetail.service.impl.OrderDetailServiceImpl;
import com.nhnacademy.shoppingmall.model.product.domain.Product;
import com.nhnacademy.shoppingmall.model.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.model.product.service.ProductService;
import com.nhnacademy.shoppingmall.model.product.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@RequestMapping(value = {"/cartOrderAction.do"}, method = RequestMapping.Method.POST)
public class CartOrderPostController implements BaseController {

    private final OrderService orderService = new OrderServiceImpl(new OrderRepositoryImpl());
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final OrderDetailService orderDetailService = new OrderDetailServiceImpl(new OrderDetailRepositoryImpl());
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession();

        String userId = "";
        User user;

        if(Objects.nonNull(session)) {
            user = (User) session.getAttribute("user");
            if (Objects.nonNull(user)) {
                userId = user.getUserId();
            }
        } else {
            log.info("로그인 해주세요");
            return "redirect:/index.do";
        }

        Map<String, Integer> shoppingCart = (Map<String,Integer>)session.getAttribute("shoppingCart");

        if(shoppingCart == null) {
            log.info("카트에 상품이 없습니다.");
            return "redirect:/index.do";
        }

        int totalPrice = 0;
        int totalCount = 0;
        List<Product> productList = new ArrayList<>();
        for(String productId : shoppingCart.keySet()) {
            Product product = productService.getProduct(productId);
            totalPrice = totalPrice + (product.getProductSalePrice() * shoppingCart.get(productId));
            totalCount = totalCount + shoppingCart.get(productId);
            productList.add(product);
        }

        int userPoint = user.getUserPoint();

        String orderId = "order" + UUID.randomUUID();

        String orderName = req.getParameter("orderName");
        String orderZipcode = req.getParameter("orderZipcode");
        String orderAddress = req.getParameter("orderAddress");
        String orderDetailAddress = req.getParameter("orderDetailAddress");
        String orderPhoneNumber = req.getParameter("orderPhoneNumber");
        String orderRequest = req.getParameter("orderRequest");


        if(userPoint > totalPrice) {
            Order order = new Order(orderId, totalPrice, Timestamp.valueOf(LocalDateTime.now()), "주문 처리", null, orderName, orderZipcode, orderAddress, orderDetailAddress, orderPhoneNumber, orderRequest, totalCount, userId);
            orderService.saveOrder(order);

            for(Product product : productList) {
                if(product.getProductQuantity() > shoppingCart.get(product.getProductId())) {
                    OrderDetail orderDetail = new OrderDetail("orderDetail" + UUID.randomUUID(), product.getProductSalePrice() * shoppingCart.get(product.getProductId()), shoppingCart.get(product.getProductId()), product.getProductId(), orderId);
                    orderDetailService.saveOrderDetail(orderDetail);
                    productService.updateProduct(
                            new Product(product.getProductId(), product.getProductName(), product.getProductQuantity() - shoppingCart.get(product.getProductId()), product.getProductImage(), product.getProductDetailImage(), product.getProductOriginalPrice(), product.getProductSalePrice(), product.getProductContent())
                    );
                    userService.updateUser(new User(user.getUserId(), user.getUserName(), user.getUserPassword(), user.getUserBirth(), user.getUserAuth(), user.getUserPoint() - totalPrice, user.getCreatedAt(), user.getLatestLoginAt()));
                    session.setAttribute("shoppingCart", new HashMap<>());
                } else {
                    log.info("상품 재고가 부족합니다. : {}", product.getProductId());
                    return "redirect:/index.do";
                }
            }
        } else {
            log.info("포인트가 부족합니다.");
            return "redirect:/index.do";
        }

        return "redirect:/index.do";
    }
}
