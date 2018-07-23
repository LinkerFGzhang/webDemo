package demo.controller;

import demo.entities.Users;
import demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(name = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute Users users, Map<String,Object> map) {

        Users user = loginService.login(users.getName(), users.getPassword());
        if (user != null){
            map.put("user",user);
            return "login_success";
        }
        return "login_fail";
    }

}
