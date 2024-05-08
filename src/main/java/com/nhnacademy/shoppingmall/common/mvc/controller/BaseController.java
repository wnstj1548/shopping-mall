package com.nhnacademy.shoppingmall.common.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface BaseController {
    String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}