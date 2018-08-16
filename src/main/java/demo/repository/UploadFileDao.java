package demo.repository;

import demo.entities.UploadFiles;

import java.util.List;

public interface UploadFileDao {
    public List<UploadFiles> getLists();

    public void uploadFile(UploadFiles uploadFile);

    public void updateFile(UploadFiles uploadFile);

    public void deleteFile(UploadFiles uploadFile);

    public UploadFiles getUploadFile(int id);
}
