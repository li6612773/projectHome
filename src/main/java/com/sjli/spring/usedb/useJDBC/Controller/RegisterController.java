package com.sjli.spring.usedb.useJDBC.Controller;

import com.sjli.spring.usedb.useJDBC.service.User;
import com.sjli.spring.usedb.useJDBC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname RegisterController
 * @Description TODO
 * @Date 2021/8/20 16:41
 * @Created by steven
 */

@Controller
public class RegisterController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public boolean doRegister(HttpServletRequest req) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        User user = userService.register(email, password, name);
        return true;
    }
}
