package demo.repository.impl;

import demo.entities.Users;
import demo.repository.LoginDao;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("loginDao")
@Transactional
public class LoginDaoImpl extends HibernateDaoSupport implements LoginDao {

    @Autowired
    public void setSessionFactory0(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public Users login(String username, String password) {
        String hql = "from Users where name=? and password=?";

        Query<Users> query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter(0, username).setParameter(1, password);
        Users temp = query.uniqueResult();
        if (temp != null) {
            return temp;
        }
        return null;
    }
}
