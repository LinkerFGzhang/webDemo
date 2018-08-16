package demo.controller;

import demo.entities.Cameras;
import demo.service.CamerasService;
import demo.utils.Result;
import demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/camera")
public class CamerasController {

    @Autowired
    private CamerasService camerasService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result cameraList() {
        Map<String, Object> map = new HashMap<>();
        System.out.println("摄像头列表...");
        map.put("cameraList", camerasService.getLists());
//        return "cameraMenu/cameraList";
        return ResultUtil.success(map);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String cameraAdd() {
        return "camearMenu/camearAdd";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String cameraSave(@RequestBody Cameras cameras) {
        System.out.println("摄像头添加信息...");
        camerasService.saveCamera(cameras);
        return "redirect:/camear/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public Result cameraUpade(@RequestBody Cameras camera) {
        System.out.println("摄像头信息更改测试...");
//        @RequestBody Map<String, Object> map
//        ObjectMapper mapper = new ObjectMapper();
//        Cameras cameras = mapper.convertValue(map.get("camera"), Cameras.class);
//        System.out.println(camera);
        // address url longitude latitutde userByUserId.id不能为空
        boolean flag = camerasService.updateCamera(camera);
//        return "success";
        if (flag) {
            return ResultUtil.success("success");
        } else {
            return ResultUtil.error();
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteCamera(@PathVariable("id") int cid) {
        System.out.println("摄像头删除测试...");
        boolean flag = camerasService.deleteCamera(cid);
        if (flag) {
            return ResultUtil.success("success");
        } else {
            return ResultUtil.error();
        }
    }
}