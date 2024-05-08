package com.nhnacademy.shoppingmall.controller.index;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = {"/cartAction.do"})
public class CartPostController implements BaseController {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        Map<String, Integer> shoppingCart = (Map<String, Integer>) session.getAttribute("shoppingCart");
        if (shoppingCart == null) {
            shoppingCart = new HashMap<>();
        }

        String productId = req.getParameter("productId");
        Integer cartQuantity = Integer.valueOf(req.getParameter("cartQuantity"));
        log.info("product id: {}, cartQuantity : {}", productId, cartQuantity);

        if(shoppingCart.get(productId) == null) {
            shoppingCart.put(productId, cartQuantity);
        } else {
            shoppingCart.put(productId, shoppingCart.get(productId) + cartQuantity);
        }

        session.setAttribute("shoppingCart", shoppingCart);

        return "redirect:/cart.do";
    }
}
