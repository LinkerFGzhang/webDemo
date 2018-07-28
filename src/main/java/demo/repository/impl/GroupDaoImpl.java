package demo.repository.impl;

import demo.entities.Groups;
import demo.repository.GroupDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupDaoImpl extends HibernateDaoSupport implements GroupDao {

    @Autowired
    public void setSessionFactory0(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public List<Groups> getGroups() {
        return (List<Groups>) getSessionFactory().getCurrentSession().createQuery("from Groups").list();
    }

    @Override
    public void saveGroup(Groups group) {
        getSessionFactory().getCurrentSession().save(group);
    }
}
