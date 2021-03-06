package demo.repository.impl;

import demo.entities.UploadFiles;
import demo.repository.UploadFileDao;
import org.hibernate.SessionFactory;
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
        return getSessionFactory().getCurrentSession().createQuery("from UploadFiles").list();
    }
}
