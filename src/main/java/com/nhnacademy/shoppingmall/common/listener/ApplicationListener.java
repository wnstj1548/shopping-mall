package com.nhnacademy.shoppingmall.common.listener;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import com.mysql.cj.jdbc.Driver;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Enumeration;

@Slf4j
@WebListener
public class ApplicationListener implements ServletContextListener {
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //todo#12 application 시작시 테스트 계정인 admin,user 등록합니다. 만약 존재하면 등록하지 않습니다.
        try {
            DbConnectionThreadLocal.initialize();
            UserService userService = new UserServiceImpl(new UserRepositoryImpl());
            User adminUser = new User("admin", "testAdmin", "12345", "20000614", User.Auth.ROLE_ADMIN, 100000 , LocalDateTime.now(), null);
            User user = new User("user", "testUser", "12345", "20000614", User.Auth.ROLE_USER, 100000 , LocalDateTime.now(), null);

            if(userService.getUser("admin") != null) {
                userService.deleteUser("admin");
            }

            if(userService.getUser("user") != null) {
                userService.deleteUser("user");
            }

            userService.saveUser(adminUser);

            userService.saveUser(user);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbConnectionThreadLocal.reset();
        }
    }
}
