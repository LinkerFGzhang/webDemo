package demo.repository.impl;

import demo.entities.UploadFiles;
import demo.repository.UploadFileDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UploadFileDaoImpl extends HibernateDaoSupport implements UploadFileDao {

    @Autowired
    public void setSessionFactory0(SessionFactory sessionFactory0) {
        super.setSessionFactory(sessionFactory0);
    }

    //获取所有的文件列表
    @Override
    public List<UploadFiles> getLists() {
        // 查询时带具体参数  将 user属性装配到 uploadfile中
        String hql = "select f from UploadFiles f inner join f.usersByUserId";
        List<UploadFiles> uploadFiles = getSessionFactory().getCurrentSession().createQuery(hql).list();
//        System.out.println("uploadfiledao -----");
//        for (UploadFiles uploadFile : uploadFiles) {
//            System.out.println(uploadFile);
//        }
        return uploadFiles;
    }

    @Override
    public void uploadFile(UploadFiles uploadFiles) {
        getSessionFactory().getCurrentSession().save(uploadFiles);
    }

    @Override
    public void updateFile(UploadFiles uploadFile) {
//        String hql = "update UploadFiles u set u.name=? where u.id=?";
//        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
//        query.setParameter(0, uploadFile.getName()).setParameter(1, uploadFile.getId());
//        query.executeUpdate();
        getSessionFactory().getCurrentSession().update(uploadFile);
    }

    @Override
    public void deleteFile(UploadFiles uploadFile) {
//        String hql = "delete from UploadFiles u where u.id=?";
//        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
//        query.setParameter(0, id);
//        query.executeUpdate();
        getSessionFactory().getCurrentSession().delete(uploadFile);
    }

    @Override
    public UploadFiles getUploadFile(int id) {
        return getSessionFactory().getCurrentSession().find(UploadFiles.class, id);
    }
}
