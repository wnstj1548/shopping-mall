package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.model.category.domain.Category;
import com.nhnacademy.shoppingmall.model.category.repository.impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.model.category.service.CategoryService;
import com.nhnacademy.shoppingmall.model.category.service.impl.CategoryServiceImpl;
import com.nhnacademy.shoppingmall.model.product.domain.Product;
import com.nhnacademy.shoppingmall.model.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.model.product.service.ProductService;
import com.nhnacademy.shoppingmall.model.product.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequestMapping(method = RequestMapping.Method.GET,value = "/admin/update.do")
public class AdminUpdateProductController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int page = 1;
        int pageSize = 9;

        String pageParam = req.getParameter("page");

        if (pageParam != null) {
            page = Integer.parseInt(pageParam);
        }

        List<Category> categoryList = categoryService.getAllCategory();
        Page<Product> productPage = productService.getAllProduct(page, pageSize);
        List<Product> productList = productPage.getContent();

        req.setAttribute("categoryList", categoryList);
        req.setAttribute("productPage", productPage);
        req.setAttribute("productList", productList);

        return "/admin/productUpdate";
    }
}
