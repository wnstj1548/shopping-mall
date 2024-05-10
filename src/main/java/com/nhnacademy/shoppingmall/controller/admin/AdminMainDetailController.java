package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.model.orderDetail.domain.OrderDetail;
import com.nhnacademy.shoppingmall.model.orderDetail.repository.impl.OrderDetailRepositoryImpl;
import com.nhnacademy.shoppingmall.model.orderDetail.service.OrderDetailService;
import com.nhnacademy.shoppingmall.model.orderDetail.service.impl.OrderDetailServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET,value = "/admin/mainDetail.do")
public class AdminMainDetailController implements BaseController {

    private final OrderDetailService orderDetailService = new OrderDetailServiceImpl(new OrderDetailRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String orderId = req.getParameter("orderId");

        List<OrderDetail> orderDetails = orderDetailService.getOrderDetailsByOrderId(orderId);
        req.setAttribute("orderDetailList", orderDetails);

        return "/admin/mainDetail";
    }
}
