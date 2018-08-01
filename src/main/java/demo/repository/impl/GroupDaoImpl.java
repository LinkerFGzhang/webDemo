package demo.repository.impl;

import demo.entities.Groups;
import demo.repository.GroupDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.nativejdbc.OracleJdbc4NativeJdbcExtractor;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
        getSessionFactory().getCurrentSession().update(group);
    }

    @Override
    public void deleteGroup(int gid) {
        getSessionFactory().getCurrentSession().delete(gid);
    }
}

