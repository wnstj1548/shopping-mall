package com.nhnacademy.shoppingmall.controller.index;


import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Slf4j
@RequestMapping(value = {"/cartOrder.do"}, method = RequestMapping.Method.GET)
public class CartOrderController implements BaseController {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession();
        Map<String, Integer> shoppingCart = (Map<String, Integer>) session.getAttribute("shoppingCart");

        if(shoppingCart == null) {
            log.info("장바구니가 비었습니다.");
            return "redirect:/index.do";
        }

        return "shop/main/order";
    }
}
