package com.sjli.spring.IOC.customBean.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//然后，我们给UserService添加一个@Component注解和一个@Autowired注解：
@Component
public class UserService {
    /*
    使用@Autowired就相当于把指定类型的Bean注入到指定的字段中。和XML配置相比，@Autowired大幅简化了注入，因为它不但可以写在set()方法上，还可以直接写在字段上，甚至可以写在构造方法中：

    @Component
    public class UserService {
        MailService mailService;

        public UserService(@Autowired MailService mailService) {
            this.mailService = mailService;
        }
        ...
    }
    我们一般把@Autowired写在字段上，通常使用package权限的字段，便于测试。
     */
    @Autowired
    private MailService mailService;

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    private List<User> users = new ArrayList<>(List.of( // users:
            new User(1, "bob@example.com", "password", "Bob"), // bob
            new User(2, "alice@example.com", "password", "Alice"), // alice
            new User(3, "tom@example.com", "password", "Tom"))); // tom

    public User login(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                mailService.sendLoginMail(user);
                return user;
            }
        }
        throw new RuntimeException("login failed.");
    }

    public User getUser(long id) {
        return this.users.stream().filter(user -> user.getId() == id).findFirst().orElseThrow();
    }

    public User register(String email, String password, String name) {
        users.forEach((user) -> {
            if (user.getEmail().equalsIgnoreCase(email)) {
                throw new RuntimeException("email exist.");
            }
        });
        User user = new User((int) (users.stream().mapToLong(u -> u.getId()).max().getAsLong() + 1), email, password, name);
        users.add(user);
        mailService.sendRegistrationMail(user);
        return user;
    }
}
