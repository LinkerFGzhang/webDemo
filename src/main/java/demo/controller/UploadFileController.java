package demo.controller;

import demo.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping(value = "/file")
public class UploadFileController {

    @Autowired
    private UploadFileService uploadFileService;

    @RequestMapping(value = "/list")
    public String fileList(Map<String, Object> map) {
        map.put("fileList", uploadFileService.getLists());
        return "fileMenu/fileList";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String fileUpload() {
        return "fileMenu/fileUpload";
    }
}
