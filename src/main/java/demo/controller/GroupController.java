package demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.entities.Groups;
import demo.entities.Users;
import demo.service.GroupService;
import demo.service.UserService;
import demo.utils.Result;
import demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value = "/group")
public class GroupController {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public ModelAndView groupList() {
//        System.out.println("组表访问...");
//        ModelAndView modelAndView = new ModelAndView("groupMenu/groupList");
//        //map.put("groupLists", groupService.getAccessibilityGroups(uid));
//        modelAndView.addObject("groupLists", groupService.getAccessibilityGroups());
//        return modelAndView;
//    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result groupList() {
        System.out.println("组表访问...");
        ModelAndView modelAndView = new ModelAndView("groupMenu/groupList");
        //map.put("groupLists", groupService.getAccessibilityGroups(uid));
        modelAndView.addObject("groupLists", groupService.getAccessibilityGroups());
        return ResultUtil.success(modelAndView);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
//    public String groupSave(Groups group) {
    public String groupSave(@RequestBody Map<String, Object> map) {
        System.out.println("组信息添加测试...");
        ObjectMapper mapper = new ObjectMapper();
        Groups group = mapper.convertValue(map.get("group"), Groups.class);
        group.setUsersByOwnerId(mapper.convertValue(map.get("usersByOwnerId"), Users.class));
//        System.out.println("groupcontroller---" + group);
//        ModelAndView modelAndView = new ModelAndView("redirect:/group/list/" + uid);
        groupService.saveGroup(group);
//        return modelAndView;
        return "success";
    }

    @RequestMapping(value = "/add/{gid}", method = RequestMethod.GET)
    public ModelAndView groupAdd(@PathVariable("gid") int gid) {
        ModelAndView modelAndView = new ModelAndView("groupMenu/groupAdd");
        //modelAndView.addObject("group", new Groups());
        //只能是group.id 为1，2的用户才能添加组，
        modelAndView.addObject("owner", userService.getAddGroupUsers(gid));
        return modelAndView;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object groupUpdate(@RequestBody Groups group) {
        System.out.println("组信息修改测试...");
        //ObjectMapper mapper = new ObjectMapper();
        //Groups group = mapper.convertValue(map.get("group"), Groups.class);
        //System.out.println(mapper.convertValue(map.get("usersByOwnerId"), Users.class));
        //group.setUsersByOwnerId(mapper.convertValue(map.get("usersByOwnerId"), Users.class));

//        System.out.println("groupController ----  " + group);
        boolean flag = groupService.updateGroup(group);
        if (flag) {
            return ResultUtil.success("success");
        } else {
            return ResultUtil.error();
        }
    }

    @RequestMapping(value = "/delete/{gId}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object groupDelete(@PathVariable("gId") int gId) {
        System.out.println("删除组信息....");
//        System.out.println("groupController  gId ---- " + gId);
        boolean flag = groupService.deleteGroup(gId);
        if (flag){
            return ResultUtil.success("success");
        } else {
            return ResultUtil.error();
        }
    }
}
