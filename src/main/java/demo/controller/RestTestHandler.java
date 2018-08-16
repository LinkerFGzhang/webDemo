package demo.controller;

import demo.entities.Cameras;
import demo.entities.UploadFiles;
import demo.entities.Users;
import demo.repository.UserDao;
import demo.service.UserService;
import demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/rest")
@Controller
public class RestTestHandler {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/methodstest/{id}", method = RequestMethod.GET)
    public String restGet(@PathVariable int id)//当@PathVariable没有标明{id}，
    {
        System.out.println("get " + id);
        System.out.println("querry operations...");
        return "querry";
    }

    @RequestMapping(value = "/methodstest", method = RequestMethod.POST)
    public String restPost() {
        System.out.println("post ");
        System.out.println("post operations...");
        return "post";
    }

    @RequestMapping(value = "/methodstest/{id}", method = RequestMethod.PUT)
    public String restPut(@PathVariable int id) {
        System.out.println("put " + id);
        System.out.println("put operations...");
        return "put";
    }

    // 测试 返回json数据
    @RequestMapping(value = "/methodstest/{id}", method = RequestMethod.DELETE)
    @ResponseBody
//    public Groups[] restDelete(@PathVariable int id, @RequestBody Groups[] groups) {
//    public List<UploadFiles> restDelete(@RequestBody Groups groups) {
    public Object restDelete(@RequestBody Users test) {
//        System.out.println("delete " + map.get("id").getClass());
//        System.out.println("delete " + map.get("id"));
//        System.out.println(id);
//        System.out.println();
//        HttpSession session = request.getSession();
//        Users user = (Users) session.getAttribute("loginUser");
//        System.out.println(user);
//        System.out.println(user.getId());
//        System.out.println(user.getGroupsByGroupId().getId());


//        System.out.println(request.getCookies().length);
//        System.out.println(request.getHeader("Cookie"));

        userService.test();

        System.out.println("delete operations...");

        // 转化json数据出错，不能 LinkedHashMap 转化为 entity 类型对象
//        ObjectMapper mapper = new ObjectMapper();
//        Cameras cameras = mapper.convertValue(map.get("camera"), Cameras.class);
//        System.out.println(cameras);


//        System.out.println(groups);
        //能够读取到对象数组
        //System.out.println(map.get("groups"));
//        ArrayList<Object> groups = (ArrayList<Object>) map.get("groups");
//        for (Object o : groups) {
//            System.out.println(o);
//        }
//        ArrayList<Groups> groups1 = (ArrayList<Groups>) map.get("groups");
//        for (Object o : groups1) {
//            System.out.println(o);
//        }
        System.out.println("测试用户-----" + test);

        Map<String, Object> result = new HashMap<>();
        Users lists = userDao.test();
//        result.put("lists", lists);
//        result.put("id", 2);
//        return lists;
        return ResultUtil.success(result);
//        return result;
//        return groups;
    }
}
