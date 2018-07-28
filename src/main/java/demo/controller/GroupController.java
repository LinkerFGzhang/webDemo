package demo.controller;

import demo.entities.Groups;
import demo.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class GroupController {

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "groupList", method = RequestMethod.GET)
    public String groupList(Map<String,Object> map) {
        map.put("groupLists",groupService.getGroups());
        return "groupList";
    }

    @RequestMapping(value = "groupAdd", method = RequestMethod.GET)
    public String groupAdd(){
        return "groupAdd";
    }

    @RequestMapping(value = "groupAdd", method = RequestMethod.POST)
    public String groupSave(Groups group){
        groupService.saveGroup(group);
        return "redirect:/groupList";
    }
}
