package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST,value = "/loginAction.do")
public class LoginPostController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        //todo#13-2 로그인 구현, session은 60분동안 유지됩니다.
        String userId = req.getParameter("user_id");
        String userPassword = req.getParameter("user_password");

        if(Objects.nonNull(userId) && Objects.nonNull(userPassword)) {
            try {
                User user = userService.doLogin(userId, userPassword);
                HttpSession session = req.getSession(true);
                log.info(session.toString());
                session.setMaxInactiveInterval(3600);
                session.setAttribute("user", user);

                if(Objects.nonNull(user)) {
                    if(user.getUserAuth() == User.Auth.ROLE_ADMIN) {
                        return "redirect:/admin/main.do";
                    }  else {
                        return "redirect:/index.do";
                    }
                }
            } catch(UserNotFoundException e) {
                log.error(" LoginPostController : 유저를 찾지 못했습니다.");
            }

        } else {
            log.error(" LoginPostController : 유저를 찾지 못했습니다.");
        }

        return "shop/login/login_form";
    }
}
