package com.nhnacademy.shoppingmall.controller.index;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.model.category.domain.Category;
import com.nhnacademy.shoppingmall.model.category.repository.impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.model.category.service.CategoryService;
import com.nhnacademy.shoppingmall.model.category.service.impl.CategoryServiceImpl;
import com.nhnacademy.shoppingmall.model.product.domain.Product;
import com.nhnacademy.shoppingmall.model.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.model.product.service.ProductService;
import com.nhnacademy.shoppingmall.model.product.service.impl.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET,value = {"/detail.do"})
public class indexDetailController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        List<String> recentlyView = (List<String>) session.getAttribute("recentlyView");
        if(recentlyView == null) {
            recentlyView = new ArrayList<>();
        }

        List<Category> categoryList = categoryService.getAllCategory();
        String productId = req.getParameter("productId");
        Product product = productService.getProduct(productId);
        req.setAttribute("product", product);
        req.setAttribute("categoryList", categoryList);

        recentlyView.remove(productId);
        recentlyView.add(0, productId);

        if (recentlyView.size() > 5) {
            recentlyView.remove(recentlyView.size() - 1);
        }

        Map<String, Integer> shoppingCart = (Map<String, Integer>) session.getAttribute("shoppingCart");
        if (shoppingCart == null) {
            shoppingCart = new HashMap<>();
        }

        session.setAttribute("shoppingCart", shoppingCart);
        session.setAttribute("recentlyView", recentlyView);

        return "shop/main/productDetail";
    }
}
