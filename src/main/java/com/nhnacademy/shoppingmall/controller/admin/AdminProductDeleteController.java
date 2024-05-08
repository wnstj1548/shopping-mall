package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.model.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.model.product.service.ProductService;
import com.nhnacademy.shoppingmall.model.product.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.model.productCategory.repository.impl.ProductCategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.model.productCategory.service.ProductCategoryService;
import com.nhnacademy.shoppingmall.model.productCategory.service.impl.ProductCateogoryServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST ,value = "/admin/deleteAction.do")
public class AdminProductDeleteController implements BaseController {
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final ProductCategoryService productCategoryService = new ProductCateogoryServiceImpl(new ProductCategoryRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String productId = req.getParameter("productId");
        productCategoryService.deletePC(productId);
        productService.deleteProduct(productId);
        return "redirect:/admin/update.do";
    }
}
