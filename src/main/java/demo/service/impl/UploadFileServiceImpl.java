package demo.service.impl;

import demo.entities.UploadFiles;
import demo.entities.Users;
import demo.repository.UploadFileDao;
import demo.repository.UserDao;
import demo.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UploadFileServiceImpl implements UploadFileService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UploadFileDao uploadFileDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<UploadFiles> getLists() {
        return uploadFileDao.getLists();
    }

    @Override
    public boolean uploadFile(MultipartFile multipartFile) {

        if (!multipartFile.isEmpty()) {
            UploadFiles uploadFile = new UploadFiles();
            //文件原始名称
            String fileName = multipartFile.getOriginalFilename();
            //文件的name
            String FsName = fileName.substring(0, fileName.lastIndexOf("."));
            //文件的后缀名 (+1 不保存"."  不加保存".")
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            //文件的类型
            String type = multipartFile.getContentType();
            //文件的创建时间
            Timestamp timestamp = new Timestamp(new Date().getTime());
            //文件所属者
            Users user = (Users) request.getSession().getAttribute("loginUser");
            //文件数据写进数据库
            uploadFile.setName(fileName);
            uploadFile.setFsName(FsName);
            uploadFile.setType(type);
            uploadFile.setCreateTime(timestamp);
            uploadFile.setSuffix(suffix);
            uploadFile.setUsersByUserId(user);
            uploadFileDao.uploadFile(uploadFile);

            // 项目在容器中实际发布运行的根路径  文件上传 System.currentTimeMillis()
            String realPath = request.getSession().getServletContext().getRealPath("/") + "file\\";
            // 自定义的文件名称
            String filePath = realPath + fileName;

            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            try {
                multipartFile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } else {
            System.out.println("上传文件不存在");
        }
        return false;
    }

    @Override
    public boolean updateFile(UploadFiles newFileInfo) {
//        System.out.println("newFileInfo----" + newFileInfo);
        Users user = (Users) request.getSession().getAttribute("loginUser");

        // 获得原本旧文件的信息
        UploadFiles oldFileInfo = uploadFileDao.getUploadFile(newFileInfo.getId());
//        System.out.println("oldFileInfo----" + oldFileInfo);
        String realPath = request.getSession().getServletContext().getRealPath("/") + "file\\";
        String filepath = realPath + oldFileInfo.getName();
//        System.out.println("文件所在位置---" + filepath);
        File oldFile = new File(filepath);
        //修改从前台传来数据
        oldFileInfo.setType(newFileInfo.getType());
        oldFileInfo.setName(newFileInfo.getName());
        oldFileInfo.setFsName(newFileInfo.getFsName());
        oldFileInfo.setSuffix(newFileInfo.getSuffix());

        String newFilePath = realPath + newFileInfo.getFsName() + "." + newFileInfo.getSuffix();

        if (user.getId() == 1 || user.getGroupsByGroupId().getId() == 1) {
            uploadFileDao.updateFile(oldFileInfo);
            oldFile.renameTo(new File(newFilePath));
            return true;
        } else if (user.getGroupsByGroupId().getId() == 2) {
            Users owner = userDao.getOwner(oldFileInfo.getUsersByUserId().getGroupsByGroupId().getId());
            if (oldFileInfo.getUsersByUserId().getId() == user.getId() || owner.getId() == user.getId()) {
                uploadFileDao.updateFile(oldFileInfo);
                oldFile.renameTo(new File(newFilePath));
                return true;
            }
        } else if (user.getGroupsByGroupId().getId() >= 3) {
            if (oldFileInfo.getUsersByUserId().getId() == user.getId()) {
                uploadFileDao.updateFile(oldFileInfo);
                oldFile.renameTo(new File(newFilePath));
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteFile(int fid) {
        Users user = (Users) request.getSession().getAttribute("loginUser");

        UploadFiles uploadFile = uploadFileDao.getUploadFile(fid);
        //文件所在位置
        String realPath = request.getSession().getServletContext().getRealPath("/") + "file\\";
        String filePath = realPath + uploadFile.getName();
        File file = new File(filePath);

        if (user.getId() == 1 || user.getGroupsByGroupId().getId() == 1) {
            uploadFileDao.deleteFile(uploadFile);
            return file.delete();
        } else if (user.getGroupsByGroupId().getId() == 2) {
            Users owner = userDao.getOwner(uploadFile.getUsersByUserId().getGroupsByGroupId().getId());
            if (uploadFile.getUsersByUserId().getId() == user.getId() || owner.getId() == user.getId()) {
                uploadFileDao.deleteFile(uploadFile);
                return file.delete();
            }
        } else if (user.getGroupsByGroupId().getId() >= 3) {
            if (uploadFile.getUsersByUserId().getId() == user.getId()) {
                uploadFileDao.deleteFile(uploadFile);
                return file.delete();
            }
        }
        return false;
    }

    @Override
    public UploadFiles getUploadFile(int id) {
        return uploadFileDao.getUploadFile(id);
    }
}
