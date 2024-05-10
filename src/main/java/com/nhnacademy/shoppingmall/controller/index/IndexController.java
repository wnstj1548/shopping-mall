package com.nhnacademy.shoppingmall.controller.index;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.model.cart.domain.Cart;
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
import java.util.*;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = {"/index.do"})
public class IndexController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession();

        //최근 본 상품
        List<String> recentlyView = (List<String>) session.getAttribute("recentlyView");
        List<Product> recentlyProduct = new ArrayList<>();
        if(recentlyView != null && Objects.nonNull(recentlyView)) {
            for(String recently : recentlyView) {
                productService.getProduct(recently);
                recentlyProduct.add(productService.getProduct(recently));
            }
        }

        //장바구니
        Map<String, Integer> shoppingCart = (Map<String, Integer>) session.getAttribute("shoppingCart");
        if (shoppingCart == null) {
            shoppingCart = new HashMap<>();
        }

        //페이징 처리
        int page = 1;
        int pageSize = 9;

        String pageParam = req.getParameter("page");

        if (pageParam != null) {
            page = Integer.parseInt(pageParam);
        }

        String categoryId = req.getParameter("categoryId");

        Page<Product> productPage;

        if(categoryId != null) {
            productPage = productService.getProductByCategoryId(page, pageSize, categoryId);
        } else {
            productPage = productService.getAllProduct(page, pageSize);
        }

        List<Category> categoryList = categoryService.getAllCategory();
        List<Product> productList = productPage.getContent();

        req.setAttribute("categoryList", categoryList);
        req.setAttribute("productPage", productPage);
        req.setAttribute("productList", productList);
        req.setAttribute("recentlyProduct", recentlyProduct);

        return "shop/main/index";
    }
}