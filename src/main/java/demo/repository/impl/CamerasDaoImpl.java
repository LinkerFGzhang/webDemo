package demo.repository.impl;

import demo.repository.CamerasDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CamerasDaoImpl extends HibernateDaoSupport implements CamerasDao {

    @Autowired
    public void setSessionFactory0(SessionFactory sessionFactory0){
        super.setSessionFactory(sessionFactory0);
    }
}
