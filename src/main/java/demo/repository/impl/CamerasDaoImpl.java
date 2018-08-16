package demo.repository.impl;

import demo.entities.Cameras;
import demo.entities.Users;
import demo.repository.CamerasDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CamerasDaoImpl extends HibernateDaoSupport implements CamerasDao {

    @Autowired
    public void setSessionFactory0(SessionFactory sessionFactory0) {
        super.setSessionFactory(sessionFactory0);
    }

    @Override
    public List<Cameras> getLists(String hql) {
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        return query.getResultList();
    }

    @Override
    public void saveCamera(Cameras cameras) {
        getSessionFactory().getCurrentSession().save(cameras);
    }

    @Override
    public void updateCamera(Cameras cameras) {
        //使用update更新时 主键不能非空 外键usersByUserId需创建对象 new Users()
        getSessionFactory().getCurrentSession().saveOrUpdate(cameras);
    }

    @Override
    public void deleteCamera(Cameras camera) {
//        String hql = "delete from Cameras c where c.id=?";
//        Query query = getSessionFactory().getCurrentSession().createQuery(hql).setParameter(0, cid);
//        query.executeUpdate();
        getSessionFactory().getCurrentSession().delete(camera);
    }

    @Override
    public Cameras getCamera(int id) {
        return getSessionFactory().getCurrentSession().find(Cameras.class, id);
    }

}
