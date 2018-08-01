package demo.controller;

import demo.entities.Groups;
import demo.service.GroupService;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(value = "/group")
public class GroupController {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/list/{uid}", method = RequestMethod.GET)
    public String groupList(@PathVariable(value = "uid", required = false) int uid, Map<String, Object> map) {
        System.out.println("组表访问...");
        map.put("groupLists", groupService.getAccessibilityGroups(uid));
        return "groupMenu/groupList";
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

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String groupUpdate(@PathVariable("id") Integer uid, Groups group) {
        groupService.updateGroup(group);
        return "redirect:/group/list/" + uid;
    }

    @RequestMapping(value = "/delete/{gid}", method = RequestMethod.DELETE)
    public void groupDelete(@PathVariable("gid") Integer gid) {
        groupService.deleteGroup(gid);
    }
}
