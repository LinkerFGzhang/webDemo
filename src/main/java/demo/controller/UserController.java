package demo.controller;

import demo.entities.Users;
import demo.repository.UserDao;
import demo.service.GroupService;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserDao userDao;

    /**
     * 显示所有用户信息
     */
    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    public String userList(@PathVariable("id") Integer uid, Map<String, Object> map) {
        System.out.println("访问用户列表...");
        List<Object[]> allGroups = groupService.getAccessibilityGroups(uid);
        map.put("allGroup", allGroups);
        Map<String, Object> temp = userService.getAccessibilityUsers(uid);
        map.putAll(temp);
        return "userMenu/userList";
    }

    /**
     * 添加用户信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String userSave(@ModelAttribute Users user) {
        userService.saveUser(user);
        return "redirect:/userList";
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public String userAdd(@PathVariable("id") Integer id, Map<String, Object> map) {
        System.out.println("添加用户测试...");
        map.put("group", groupService.getGroups());
        map.put("user", new Users());
        return "userMenu/userAdd";
    }

    /**
     * 删除用户信息
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    private String userDelete(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/userList";
    }

    /**
     * 修改用户信息
     */
    @RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
    public String userEdit(@PathVariable("id") Integer id, Map<String, Object> map) {
        map.put("group", groupService.getGroups());
        return "userMenu/userEdit";
    }

    @ModelAttribute
    public void getUser(@PathVariable(value = "id", required = false) Integer id, Map<String, Object> map) {
        if (id != null) {
            map.put("user", userService.getUser(id));
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String userUpdate(@PathVariable("id") Integer id, @ModelAttribute Users user, Map<String, Users> map) {

        userService.updateUser(user);
        return "redirect:/userList";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test() {
        userDao.test();
    }
}