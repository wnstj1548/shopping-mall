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
@RequestMapping(value = {"/cartDeleteAction.do"}, method = RequestMapping.Method.POST)
public class CartDeleteController implements BaseController {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String cartDeleteProductId = req.getParameter("cartDeleteProductId");
        log.info("cartDeleteProductId={}", cartDeleteProductId);
        Map<String, Integer> shoppingCart = (Map<String, Integer>) session.getAttribute("shoppingCart");
        if (shoppingCart != null) {
            if(shoppingCart.containsKey(cartDeleteProductId)) {
                shoppingCart.remove(cartDeleteProductId);
            }
        }

        return "redirect:/cart.do";
    }
}
