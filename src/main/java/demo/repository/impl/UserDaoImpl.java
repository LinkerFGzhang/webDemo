package demo.repository.impl;

import demo.entities.Users;
import demo.repository.UserDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

    @Autowired
    public void setSessionFactory0(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public List<Users> getAccessibilityUsers(String hql) {
        return getSessionFactory().getCurrentSession().createQuery(hql).list();
    }

    @Override
    public void updateUser(Users user) {
        getSessionFactory().getCurrentSession().update(user);
    }

    @Override
    public void saveUser(Users user) {
        getSessionFactory().getCurrentSession().save(user);
    }

    @Override
    public void deleteUser(Users user) {
        getSessionFactory().getCurrentSession().delete(user);
    }

    @Override
    public Users getUser(Integer id) {
        return getSessionFactory().getCurrentSession().get(Users.class, id);
    }

    @Override
    public List<Users> getAddGroupUsers(int gid) {
        List<Users> obj = null;
        if (gid == 1) {
            String hql = "select u from Groups g inner join g.usersByOwnerId u where g.id <=2 order by u.id";
            obj = getSessionFactory().getCurrentSession().createQuery(hql).list();
            System.out.println(obj);
        }
        return null;
    }

    @Override
    public Users getOwner(int gid) {
        String sql = "select u from Groups g inner join g.usersByOwnerId u where g.id=?";
        return (Users) getSessionFactory().getCurrentSession().createQuery(sql).setParameter(0,gid).uniqueResult();
    }

    public Users test() {
        //顺序不一样，json返回的格式不同
//        String sql = "select u from Users u inner join u.groupsByGroupId g";
//        String sql = "select g from Groups g inner join fetch g.usersByOwnerId u";
        // 没有 fetch 的用法  相当于将查询结果分别封装到两个实体当中
//        String sql = "select u from UploadFiles u inner join u.usersByUserId";
//        删除信息
//        int id = 13;
//        String hql = "delete from Cameras c where c.id=?";
//        Query query = getSessionFactory().getCurrentSession().createQuery(hql).setParameter(0, id);
//        query.executeUpdate();

//        修改信息
//        Groups groups = new Groups();
//        groups.setId(16);
//        groups.setName("adadasd111");
//        groups.setDescription("test111");
//        groups.setUsersByOwnerId(new Users(1));
//        getSessionFactory().getCurrentSession().update(groups);

//        String hql = "update from Groups g set g.name=?,g.description=?,g.usersByOwnerId=? where g.id=16";
//        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
//        query.setParameter(0,"test1").setParameter(1,"test1").setParameter(2,new Users(2));
//        query.executeUpdate();

//        String sql = "select c from Cameras c inner join c.usersByUserId u order by c.id";
//        List<UploadFiles> list = getSessionFactory().getCurrentSession().createQuery(sql).getResultList();

        String sql = "select u from Groups g inner join g.usersByOwnerId u where g.id = 11";
        Users list = (Users) getSessionFactory().getCurrentSession().createQuery(sql).uniqueResult();
        System.out.println(list);

        return list;
    }
}
