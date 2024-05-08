package com.nhnacademy.shoppingmall.controller.index;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.model.product.domain.Product;
import com.nhnacademy.shoppingmall.model.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.model.product.service.ProductService;
import com.nhnacademy.shoppingmall.model.product.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = {"/cart.do"}, method = RequestMapping.Method.GET)
public class CartController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession();
        Map<String, Integer> shoppingCart = (Map<String, Integer>) session.getAttribute("shoppingCart");
        if (shoppingCart == null) {
            shoppingCart = new HashMap<>();
        }
        List<Product> cartProductList = new ArrayList<>();

        if (!shoppingCart.isEmpty()) {
            shoppingCart.keySet().forEach(key -> {
                Product product = productService.getProduct(key);
                if (product != null) {
                    cartProductList.add(product);
                }});
        }

        req.setAttribute("cartProductList", cartProductList);
        req.setAttribute("shoppingCart", shoppingCart);

        return "shop/main/cart";
    }
}
