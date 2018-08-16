package demo.controller;

import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/testPut", method = RequestMethod.PUT)
    public void testPut() {
        System.out.println("testPut.....");
    }

    @RequestMapping(value = "/testDelete", method = RequestMethod.DELETE)
    public void testDelete() {
        System.out.println("testDelete.....");
    }
}
