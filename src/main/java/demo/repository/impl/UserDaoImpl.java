package demo.repository.impl;

import demo.entities.Groups;
import demo.entities.Users;
import demo.repository.UserDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

@Repository
@Transactional
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

    @Autowired
    public void setSessionFactory0(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public Map<String, Object> getAllUser(Integer uid) {
        //存放信息的map
        Map<String, Object> map = new HashMap<String, Object>();
        if (uid == 1) {
            map.put("currentUser", "超级管理员");
            //用户组信息
            String hql1 = "select g.id,g.description from Groups g order by g.id";
            Object object = getSessionFactory().getCurrentSession().createQuery(hql1).list();
            map.put("allGroup", object);
            //用户信息
            String hql2 = "select u,g.description from Users u inner join u.groupsByGroupId g order by g.id";
            Object object1 = getSessionFactory().getCurrentSession().createQuery(hql2).list();
            map.put("allUser", object1);
        } else {
            String hql3 = "select g.id from Users u inner join u.groupsByGroupId g where u.id=?";
            //当前用户所在组
            Integer gid = (Integer) getSessionFactory().getCurrentSession().createQuery(hql3).setParameter(0, uid).uniqueResult();

            System.out.println("gid------>" + gid);
            map.put("groupId", gid);
            if (gid == 1) {
                String hql4 = "select g.id,g.description from Groups g where g.id > 1 order by g.id";
                List<Groups> allGroup = getSessionFactory().getCurrentSession().createQuery(hql4).list();

                System.out.println("可查看的用户组有------>" + allGroup.size());
                map.put("allGroup", allGroup);
                //查询大于该分组的数据
                String hql5 = "select u,g.description from Users u inner join u.groupsByGroupId g where g.id >1 order by g.id";
                List<Object[]> allUser = getSessionFactory().getCurrentSession().createQuery(hql5).list();

                System.out.println("查询到数据库的用户有-------" + allUser.size());
                map.put("allUser", allUser);
            } else if (gid == 2) {
                String hql6 = "select g.id,g.description from Groups g where g.id=? order by g.id";
                map.put("allGroup", getSessionFactory().getCurrentSession().createQuery(hql6).setParameter(0, gid).uniqueResult());
                //高级用户只能查看自己组下的用户
                String hql7 = "select u,g.description from Users u inner join u.groupsByGroupId g where u.groupsByGroupId in " +
                        "(select groupsByGroupId from Users where id=2)";
                Object object4 = getSessionFactory().getCurrentSession().createQuery(hql7).list();
                map.put("allUser", object4);
            } else {
                map.put("allGroup", null);
                map.put("allUser", null);
            }
        }
        return map;
    }

    @Override
    public void updateUser(Users user) {
        getSessionFactory().getCurrentSession().update(user);
    }

    @Override
    public void saveUser(Users user) {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        user.setCreateTime(timestamp);
        getSessionFactory().getCurrentSession().save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        getSessionFactory().getCurrentSession().delete(id);
    }

    @Override
    public Users getUser(Integer id) {
        return getSessionFactory().getCurrentSession().get(Users.class, id);
    }

    public void test() {
        String sql = "from Users u inner join u.groupsByGroupId g";
        // 没有 fetch 的用法  相当于将查询结果分别封装到两个实体当中
        List<Object[]> list = getSessionFactory().getCurrentSession().createQuery(sql).list();
        for (Object[] object : list) {
            System.out.println(Arrays.toString(object));
        }
        // 有 fetch 的用法  相当于把外键所关联的表的字段封装到主表中的相应字段中
//        String hql = "from Users u inner join fetch u.groupsByGroupId g";
//        List<Users> list1 = getSessionFactory().getCurrentSession().createQuery(hql).list();
//        for (Users users : list1) {
//            System.out.println(users);
//        }
    }
}
