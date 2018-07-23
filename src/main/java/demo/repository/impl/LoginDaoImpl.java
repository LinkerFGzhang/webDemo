package demo.repository.impl;

import demo.entities.Users;
import demo.repository.LoginDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@Repository("loginDao")
public class LoginDaoImpl extends HibernateDaoSupport implements LoginDao {

    @Resource(name = "sessionFactory")
    public void setSessionFactoryOverride(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public Users login(String username, String password) {
        String hql = "from User where name=? and password=?";

        /**
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter(0, username).setParameter(1, password);
        List<Users> users = query.list();
*/
        List<Users> users = (List<Users>) getHibernateTemplate().find(hql,username,password);
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }
}
