package demo.controller;

import demo.entities.Users;
import demo.service.GroupService;
import demo.service.UserService;
import demo.utils.Result;
import demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    /**
     * 显示所有用户信息
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
//    public Map userList(@PathVariable(value = "id",required = false) Integer uid, Map<String, Object> map) {
    public Result userList(Map<String, Object> map) {
        System.out.println("访问用户列表...");
        List<Object[]> allGroups = groupService.getAccessibilityGroups();
        map.put("allGroup", allGroups);
        map.put("allUser", userService.getAccessibilityUsers());
//        return "userMenu/userList";
        return ResultUtil.success(map);
    }

    /**
     * 添加用户信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result userSave(@RequestBody Users user) {
        System.out.println("添加用户信息测试...");
        System.out.println("usercontroller---" + user);
        boolean flag = userService.saveUser(user);
        if (flag) {
            return ResultUtil.success("success");
        } else {
            return ResultUtil.error();
        }
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
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    private Result userDelete(@PathVariable("id") Integer id) {
        boolean flag = userService.deleteUser(id);
        if (flag) {
            return ResultUtil.success("success");
        } else {
            return ResultUtil.error();
        }
    }

    /**
     * 修改用户信息
     */
    @RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
    public String userEdit(@PathVariable("id") Integer id, Map<String, Object> map) {
        map.put("group", groupService.getGroups());
        return "userMenu/userEdit";
    }

//    @ModelAttribute
//    public void getUser(@PathVariable(value = "id", required = false) Integer id, Map<String, Object> map) {
//        if (id != null) {
//            map.put("user", userService.getUser(id));
//        }
//    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Result userUpdate(@RequestBody Users user) {
        System.out.println("用户修改信息测试...");
//        System.out.println(user);
        boolean flag = userService.updateUser(user);
        if (flag) {
            return ResultUtil.success("success");
        } else {
            return ResultUtil.error();

        }
    }
}