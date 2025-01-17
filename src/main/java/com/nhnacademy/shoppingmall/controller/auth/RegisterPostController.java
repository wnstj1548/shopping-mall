package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.model.product.domain.Product;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.user.exception.UserRegisterDifferentPasswordException;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/registerAction.do")
public class RegisterPostController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

//        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
//        Validator validator = validatorFactory.getValidator();

        String userName = req.getParameter("user_name");
        String userId = req.getParameter("user_id");
        String userPassword = req.getParameter("user_password");
        String userPassword2 = req.getParameter("user_password2");
        String userBirth = req.getParameter("user_birth");


        if(Objects.nonNull(userName) && Objects.nonNull(userId) && Objects.nonNull(userPassword) && Objects.nonNull(userPassword2) && Objects.nonNull(userBirth)) {

            if(!userPassword.equals(userPassword2)) {
                throw new UserRegisterDifferentPasswordException();
            }

            if(userService.getUser(userId) != null) {
                throw new UserAlreadyExistsException(userId);
            }
            User user = new User(userId, userName, userPassword, userBirth, User.Auth.ROLE_USER, 1000000, LocalDateTime.now(), null);

//            Set<ConstraintViolation<User>> violations = validator.validate(user);
//
//            if (!violations.isEmpty()) {
//                for (ConstraintViolation<User> violation : violations) {
//                    log.error("Validation error: {}", violation.getMessage());
//                }
//                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                return "redirect:/register.do";
//            }

            userService.saveUser(user);

            return "redirect:/login.do";
        }


        return "redirect:/index.do";
    }
}
