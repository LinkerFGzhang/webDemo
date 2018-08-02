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
    public List<Object[]> getAccessibilityGroups(int uid) {
        List<Object[]> allGroup = null;
        //当前用户所在组
        String hql3 = "select g.id from Users u inner join u.groupsByGroupId g where u.id=?";
        Integer gid = (Integer) getSessionFactory().getCurrentSession().createQuery(hql3).setParameter(0, uid).uniqueResult();
        System.out.println("gid------>" + gid);
        if (gid == 1) {
            //除 g.id=1 外均可访问
            String hql4 = "from Groups g inner  join g.usersByOwnerId u where g.id > 1 order by g.id";
            allGroup = getSessionFactory().getCurrentSession().createQuery(hql4).list();
        } else if (gid == 2) {
            //可访问的组
            String hql6 = "from Groups g inner join g.usersByOwnerId where g.id=? order by g.id";
            allGroup = getSessionFactory().getCurrentSession().createQuery(hql6).setParameter(0, gid).list();
        }
        return allGroup;
    }

    @Override
    public void updateGroup(Groups group) {
        String hql = "update Groups g set g.name=?,g.description=? where g.id=?";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql).setParameter(0, group.getName());
        query.setParameter(0, group.getName()).setParameter(1, group.getDescription()).setParameter(2, group.getId());
        query.executeUpdate();
    }

    @Override
    public void deleteGroup(int gid) {
        String hql = "delete from Groups g where g.id=?";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql).setParameter(0, gid);
        query.executeUpdate();
    }
}