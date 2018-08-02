package demo.controller;

import demo.entities.Groups;
import demo.entities.Users;
import demo.service.GroupService;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping(value = "/group")
@RestController
public class GroupController {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/list/{uid}", method = RequestMethod.GET)
    public ModelAndView groupList(@PathVariable(value = "uid") int uid) {
        System.out.println("组表访问...");
        ModelAndView modelAndView = new ModelAndView("groupMenu/groupList");
        //map.put("groupLists", groupService.getAccessibilityGroups(uid));
        modelAndView.addObject("groupLists",groupService.getAccessibilityGroups(uid));
        return modelAndView;
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public String groupAdd(@PathVariable("id") int gid, Map<String, Object> map) {
        map.put("group", new Groups());
        //只能是group.id 为1，2的用户才能添加组，
        map.put("owner", userService.getAddGroupUsers(gid));
        return "groupMenu/groupAdd";
    }

    @RequestMapping(value = "/add/{uid}", method = RequestMethod.POST)
    public String groupSave(@PathVariable int uid, Groups group) {
        groupService.saveGroup(group);
        return "redirect:/groupList";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public void groupUpdate(Groups group, HttpRequest request) {
        System.out.println(group);
        System.out.println("url地址为：" + request.getURI());
        System.out.println("组信息修改测试...");
        groupService.updateGroup(group);
        return ;
    }

    @RequestMapping(value = "/delete/{gid}", method = RequestMethod.DELETE)
    public void groupDelete(@PathVariable("gid") Integer gid, HttpRequest request) {
        System.out.println("删除组信息....");
        System.out.println(request.getURI());
        System.out.println("groupController ---- " + gid);
        groupService.deleteGroup(gid);
    }
}
