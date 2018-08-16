package demo.controller;

import demo.entities.UploadFiles;
import demo.entities.Users;
import demo.service.UploadFileService;
import demo.utils.Result;
import demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping(value = "/file")
public class UploadFileController {

    @Autowired
    private UploadFileService uploadFileService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result fileList(Map<String, Object> map) {
        System.out.println("文件列表测试...");
        map.put("fileList", uploadFileService.getLists());
//        return "fileMenu/fileList";
        return ResultUtil.success(map);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String fileUpload() {
        return "fileMenu/fileUpload";
    }

    //enctype="multipart/form-data"
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String fileUpdload(@RequestParam("file") MultipartFile multipartFile) {
        System.out.println("文件上传测试...");
        boolean flag = uploadFileService.uploadFile(multipartFile);
        if (flag){
            return "redirect:/file/list";
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public Object fileUpdate(@RequestBody UploadFiles newFileInfo) {
        System.out.println("文件修改测试...");
//        System.out.println(newFileInfo);
        boolean flag = uploadFileService.updateFile(newFileInfo);
        if (flag) {
            return ResultUtil.success("success");
        } else {
            return ResultUtil.error();
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object fileDelete(@PathVariable("id") int id) {
        System.out.println("文件删除测试...");
//        System.out.println(id);
        boolean flag = uploadFileService.deleteFile(id);
        if (flag){
            return ResultUtil.success("success");
        } else{
            return ResultUtil.error();
        }
//        return "redirect:/file/list";
    }
}
