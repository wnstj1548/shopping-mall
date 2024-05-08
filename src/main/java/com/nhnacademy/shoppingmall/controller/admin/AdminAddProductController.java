package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.model.category.domain.Category;
import com.nhnacademy.shoppingmall.model.category.repository.impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.model.category.service.CategoryService;
import com.nhnacademy.shoppingmall.model.category.service.impl.CategoryServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET,value = "/admin/add.do")
public class AdminAddProductController implements BaseController {

    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Category> categoryList = categoryService.getAllCategory();

        req.setAttribute("categoryList", categoryList);

        return "/admin/productAdd";
    }
}
