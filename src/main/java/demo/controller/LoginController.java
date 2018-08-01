package demo.controller;

import demo.entities.Groups;
import demo.entities.Users;
import demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String tologin() {
        return "login";
    }

    @RequestMapping(name = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute Users temp, HttpSession session) {
        Users user = loginService.login(temp.getName(), temp.getPassword());
        System.out.println(user);
        if (user != null) {
            session.setAttribute("loginUser", user);
            Groups groups = loginService.getGroup(user.getId());
            System.out.println(groups);
            session.setAttribute("loginGroup", groups);
            return "redirect:menu";
        } else {
            session.setAttribute("message", "用户名或密码错误");
            return "login_fail";
        }
    }

//    @RequestMapping(value = "/login" ,method = RequestMethod.POST)
//    //spring4.x是org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
//    @ResponseBody
//    public Users login(Users users, HttpSession session){
//        Users user = loginService.login(users.getName(), users.getPassword());
//        if (user != null) {
//            session.setAttribute("user",user);
//            return user;
//        } else {
//            session.setAttribute("message","用户名或密码不正确..");
//            return null;
//        }
//    }

    @RequestMapping(name = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index.jsp";
    }
}
