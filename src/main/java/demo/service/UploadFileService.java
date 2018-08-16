package demo.service;

import demo.entities.UploadFiles;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UploadFileService {

    public List<UploadFiles> getLists();

    public boolean uploadFile(MultipartFile multipartFile);

    public boolean updateFile(UploadFiles uploadFile);

    public boolean deleteFile(int id);

    public UploadFiles getUploadFile(int id);
}
