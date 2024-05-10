package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
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
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/addAction.do")
public class AdminAddProductPostController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final ProductCategoryService productCategoryService = new ProductCateogoryServiceImpl(new ProductCategoryRepositoryImpl());

    private static final String UPLOAD_DIR = "/Users/kimjunseo/Documents/servlet-jsp-shoppingmall/servlet-jsp-shoppingmall/src/main/webapp/resources/";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

//        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
//        Validator validator = validatorFactory.getValidator();

        MultipartRequest multipartRequest = new MultipartRequest(req, UPLOAD_DIR, 1024*1024*20,"utf-8",new DefaultFileRenamePolicy());

        String productId = "product" + UUID.randomUUID().toString();
        String productName = multipartRequest.getParameter("productName");
        int productQuantity = Integer.parseInt(multipartRequest.getParameter("productQuantity"));
        int productOriginalPrice = Integer.parseInt(multipartRequest.getParameter("productOriginalPrice"));
        int productSalePrice = Integer.parseInt(multipartRequest.getParameter("productSalePrice"));
        String productContent = multipartRequest.getParameter("productContent");
        String productImagePath =  "/resources/" + multipartRequest.getFilesystemName("productImage");
        String productDetailImagePath = "/resources/" + multipartRequest.getFilesystemName("productDetailImage");

        Product product = new Product(productId, productName, productQuantity, productImagePath, productDetailImagePath, productOriginalPrice, productSalePrice, productContent);

//        Set<ConstraintViolation<Product>> violations = validator.validate(product);

//        if (!violations.isEmpty()) {
//            for (ConstraintViolation<Product> violation : violations) {
//                log.error("Validation error: {}", violation.getMessage());
//            }
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            return "/admin/addProductForm";
//        }

        productService.saveProduct(product);

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

        return "redirect:/admin/main.do";
    }
}
