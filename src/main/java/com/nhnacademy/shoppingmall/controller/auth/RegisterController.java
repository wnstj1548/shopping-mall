package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/register.do")
public class RegisterController implements BaseController {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession();
        if(Objects.nonNull(session)) {
            User user = (User) session.getAttribute("user");
            if(Objects.nonNull(user)) {
                return "redirect:/index.do";
            }
        }

        return "shop/login/register_form";
    }
}
