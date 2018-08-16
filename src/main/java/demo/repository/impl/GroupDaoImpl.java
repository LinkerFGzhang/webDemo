package demo.repository.impl;

import demo.entities.Groups;
import demo.repository.GroupDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class GroupDaoImpl extends HibernateDaoSupport implements GroupDao {

    @Autowired
    public void setSessionFactory0(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public List<Groups> getGroups() {
        //获得所有的分组
        return getSessionFactory().getCurrentSession().createQuery("from Groups").list();
    }

    @Override
    public void saveGroup(Groups group) {
        getSessionFactory().getCurrentSession().save(group);
    }

    @Override
    public List<Object[]> getAccessibilityGroups(String hql) {
        List<Object[]> allGroup = getSessionFactory().getCurrentSession().createQuery(hql).list();
        return allGroup;
    }

    @Override
    public void updateGroup(Groups group) {
//        String hql = "update Groups g set g.name=?,g.description=? where g.id=?";
//        Query query = getSessionFactory().getCurrentSession().createQuery(hql).setParameter(0, group.getName());
//        query.setParameter(0, group.getName()).setParameter(1, group.getDescription()).setParameter(2, group.getId());
//        query.executeUpdate();
        getSessionFactory().getCurrentSession().save(group);
    }

    @Override
    public void deleteGroup(int gid) {
        String hql = "delete from Groups g where g.id=?";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql).setParameter(0, gid);
        query.executeUpdate();
    }

    public Groups getGroupById(int id) {
        return getSessionFactory().getCurrentSession().find(Groups.class, id);
    }
}