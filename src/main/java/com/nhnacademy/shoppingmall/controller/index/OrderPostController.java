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
import com.nhnacademy.shoppingmall.model.pointLog.domain.PointLog;
import com.nhnacademy.shoppingmall.model.pointLog.repository.impl.PointLogRepositoryImpl;
import com.nhnacademy.shoppingmall.model.pointLog.service.PointLogService;
import com.nhnacademy.shoppingmall.model.pointLog.service.impl.PointLogServiceImpl;
import com.nhnacademy.shoppingmall.model.product.domain.Product;
import com.nhnacademy.shoppingmall.model.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.model.product.service.ProductService;
import com.nhnacademy.shoppingmall.model.product.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.thread.channel.RequestChannel;
import com.nhnacademy.shoppingmall.thread.request.impl.PointChannelRequest;
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
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequestMapping(value = {"/orderAction.do"}, method = RequestMapping.Method.POST)
public class OrderPostController implements BaseController {

    private final OrderService orderService = new OrderServiceImpl(new OrderRepositoryImpl());
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final OrderDetailService orderDetailService = new OrderDetailServiceImpl(new OrderDetailRepositoryImpl());
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    private final PointLogService pointLogService = new PointLogServiceImpl(new PointLogRepositoryImpl());

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

        int userPoint = user.getUserPoint();
        String productId = req.getParameter("productId");
        Product product = productService.getProduct(productId);

        String orderId = "order" + UUID.randomUUID();

        int productCount = Integer.parseInt(req.getParameter("productCount"));
        String orderName = req.getParameter("orderName");
        String orderZipcode = req.getParameter("orderZipcode");
        String orderAddress = req.getParameter("orderAddress");
        String orderDetailAddress = req.getParameter("orderDetailAddress");
        String orderPhoneNumber = req.getParameter("orderPhoneNumber");
        String orderRequest = req.getParameter("orderRequest");

        if(userPoint > product.getProductSalePrice() * productCount) {
            if(product.getProductQuantity() > productCount) {
                Order order = new Order(orderId, product.getProductSalePrice() * productCount, Timestamp.valueOf(LocalDateTime.now()), "주문 처리", null, orderName, orderZipcode, orderAddress, orderDetailAddress, orderPhoneNumber, orderRequest, productCount, userId);
                OrderDetail orderDetail = new OrderDetail("orderDetail" + UUID.randomUUID(), product.getProductSalePrice() * productCount, productCount, productId, orderId);
                orderService.saveOrder(order);
                orderDetailService.saveOrderDetail(orderDetail);
                productService.updateProduct(
                        new Product(product.getProductId(), product.getProductName(), product.getProductQuantity() - productCount, product.getProductImage(), product.getProductDetailImage(), product.getProductOriginalPrice(), product.getProductSalePrice(), product.getProductContent())
                );
                userService.updateUser(new User(user.getUserId(), user.getUserName(), user.getUserPassword(), user.getUserBirth(), user.getUserAuth(), user.getUserPoint() - (product.getProductSalePrice() * productCount), user.getCreatedAt(), user.getLatestLoginAt()));
                log.info("user : {}", user);
                pointLogService.savePointLog(new PointLog("point" + UUID.randomUUID(), Timestamp.valueOf(LocalDateTime.now()), -(int)(product.getProductSalePrice() * productCount), user.getUserId()));
                log.info("order : {}, orderDetail : {}", order, orderDetail);

                PointChannelRequest pointChannelRequest = new PointChannelRequest( user.getUserPoint() - (product.getProductSalePrice() * productCount) + ((product.getProductSalePrice() * productCount)/10), user.getUserId());
                RequestChannel requestChannel = (RequestChannel) req.getServletContext().getAttribute("requestChannel");
                try {
                    requestChannel.addRequest(pointChannelRequest);
                    pointLogService.savePointLog(new PointLog("point" + UUID.randomUUID(), Timestamp.valueOf(LocalDateTime.now()), (int)(product.getProductSalePrice() * productCount)/10, user.getUserId()));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            } else {
                log.info("재고가 부족합니다.");
                return "redirect:/index.do";
            }
        } else {
            log.info("포인트가 부족합니다.");
            return "redirect:/index.do";
        }
        log.info("user : {}", user);

        return "redirect:/index.do";
    }
}
