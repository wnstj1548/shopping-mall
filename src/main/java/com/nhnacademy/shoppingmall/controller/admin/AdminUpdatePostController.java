package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.model.category.repository.impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.model.category.service.CategoryService;
import com.nhnacademy.shoppingmall.model.category.service.impl.CategoryServiceImpl;
import com.nhnacademy.shoppingmall.model.product.domain.Product;
import com.nhnacademy.shoppingmall.model.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.model.product.service.ProductService;
import com.nhnacademy.shoppingmall.model.product.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.model.productCategory.domain.ProductCategory;
import com.nhnacademy.shoppingmall.model.productCategory.repository.impl.ProductCategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.model.productCategory.service.ProductCategoryService;
import com.nhnacademy.shoppingmall.model.productCategory.service.impl.ProductCateogoryServiceImpl;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST ,value = "/admin/updateAction.do")
public class AdminUpdatePostController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());
    private final ProductCategoryService productCategoryService = new ProductCateogoryServiceImpl(new ProductCategoryRepositoryImpl());
    private static final String UPLOAD_DIR = "/Users/kimjunseo/Documents/servlet-jsp-shoppingmall/servlet-jsp-shoppingmall/src/main/webapp/resources/upload";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        req.setCharacterEncoding("UTF-8");

        MultipartRequest multipartRequest = new MultipartRequest(req, UPLOAD_DIR, 1024*1024*20,"utf-8",new DefaultFileRenamePolicy());

        String productId = multipartRequest.getParameter("productId");

        String productName = multipartRequest.getParameter("productName");
        int productQuantity = Integer.parseInt(multipartRequest.getParameter("productQuantity"));
        int productOriginalPrice = Integer.parseInt(multipartRequest.getParameter("productOriginalPrice"));
        int productSalePrice = Integer.parseInt(multipartRequest.getParameter("productSalePrice"));
        String productContent = multipartRequest.getParameter("productContent");
        String productImagePath =  "/resources/upload/" + multipartRequest.getFilesystemName("productImage");
        String productDetailImagePath = "/resources/upload/" + multipartRequest.getFilesystemName("productDetailImage");

        Product product = new Product(productId, productName, productQuantity, productImagePath, productDetailImagePath, productOriginalPrice, productSalePrice, productContent);

        productService.updateProduct(product);

        productCategoryService.deletePC(productId);
        String category1 = multipartRequest.getParameter("category1");
        String category2 = multipartRequest.getParameter("category2");
        String category3 = multipartRequest.getParameter("category3");
        productCategoryService.savePC(new ProductCategory(productId, category1));
        if(Objects.nonNull(category2) && !category2.equals("null")) {
            productCategoryService.savePC(new ProductCategory(productId, category2));
        }
        if(Objects.nonNull(category3) && !category3.equals("null")) {
            productCategoryService.savePC(new ProductCategory(productId, category3));
        }

        return "redirect:/admin/update.do";
    }
}
