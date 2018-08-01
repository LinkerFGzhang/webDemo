package demo.controller;

import com.alibaba.fastjson.JSONObject;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MenuController {

    @Autowired
    private UserService userService;

    @RequestMapping("/menu")
    public String loginMenu() {
        return "menu";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String toHome() {
        return "home";
    }

}
