package demo.service.impl;

import demo.entities.UploadFiles;
import demo.repository.UploadFileDao;
import demo.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UploadFileServiceImpl implements UploadFileService {

    @Autowired
    private UploadFileDao uploadFileDao;

    @Override
    public List<UploadFiles> getLists() {
        return uploadFileDao.getLists();
    }
}
