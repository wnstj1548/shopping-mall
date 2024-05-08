package com.nhnacademy.shoppingmall.controller.admin;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequestMapping(method = RequestMapping.Method.GET,value = "/admin/updateForm.do")
public class AdminUpdateFormController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        List<Category> categoryList = categoryService.getAllCategory();
        req.setAttribute("categoryList", categoryList);

        String productId = req.getParameter("productId");
        Product product = productService.getProduct(productId);
        req.setAttribute("product", product);

        return "/admin/productAdd";
    }
}
