package demo.service.impl;

import demo.entities.Users;
import demo.repository.UserDao;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserDao userDao;

    @Override
    public List<Users> getAccessibilityUsers() {
        Users loginUser = (Users) request.getSession().getAttribute("loginUser");
        String hql = null;

        if (loginUser.getId() == 1) {
            hql = "select u from Users u inner join u.groupsByGroupId";
        } else {
            if (loginUser.getGroupsByGroupId().getId() == 1) {
                hql = "select u from Users u inner join u.groupsByGroupId g where g.id >1";
            } else if (loginUser.getGroupsByGroupId().getId() == 2) {
                hql = "select u from Users u inner join u.groupsByGroupId g where u.id=" + loginUser.getGroupsByGroupId().getId();
            }
        }

        return userDao.getAccessibilityUsers(hql);
    }

    @Override
    public boolean saveUser(Users user) {
        //创建时间
        Timestamp timestamp = new Timestamp(new Date().getTime());
        user.setCreateTime(timestamp);

        Users loginUser = (Users) request.getSession().getAttribute("loginUser");
        Users owner = userDao.getOwner(user.getGroupsByGroupId().getId());

        if (loginUser.getId() == 1) {
            userDao.saveUser(user);
            return true;
        } else {
            if (loginUser.getGroupsByGroupId().getId() == 1 && user.getGroupsByGroupId().getId() > 1) {
                userDao.saveUser(user);
                return true;
            } else if (loginUser.getGroupsByGroupId().getId() == 2 && owner.getId() == loginUser.getId()) {
                userDao.saveUser(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateUser(Users user) {
        Users loginUser = (Users) request.getSession().getAttribute("loginUser");

//        update oldUserInfo 信息
        Users oldUserInfo = userDao.getUser(user.getId());
        Users owner = userDao.getOwner(oldUserInfo.getGroupsByGroupId().getId());
//        System.out.println("userServiceImpl---" + oldUserInfo);
        oldUserInfo.setName(user.getName());
        oldUserInfo.setToken(user.getToken());
        oldUserInfo.setGenericName(user.getGenericName());
        oldUserInfo.setPassword(user.getPassword());

        if (loginUser.getId() == 1) {
            userDao.updateUser(user);
            return true;
        } else {
            if (loginUser.getGroupsByGroupId().getId() == 1 &&
                    (oldUserInfo.getGroupsByGroupId().getId() > 1 || oldUserInfo.getId() == loginUser.getId())) {
                oldUserInfo.setGroupsByGroupId(user.getGroupsByGroupId());
                userDao.updateUser(oldUserInfo);
                return true;
            } else if (loginUser.getGroupsByGroupId().getId() == 2
                    && (owner.getId() == loginUser.getId() || oldUserInfo.getId() == loginUser.getId())) {
                oldUserInfo.setGroupsByGroupId(user.getGroupsByGroupId());
                userDao.updateUser(oldUserInfo);
                return true;
            } else if (loginUser.getGroupsByGroupId().getId() >= 3 && oldUserInfo.getId() == loginUser.getId()){
                oldUserInfo.setGroupsByGroupId(user.getGroupsByGroupId());
                userDao.updateUser(oldUserInfo);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteUser(Integer id) {
        Users loginUser = (Users) request.getSession().getAttribute("loginUser");
        Users user = userDao.getUser(id);
        Users owner = userDao.getOwner(user.getGroupsByGroupId().getId());

        if (loginUser.getId() == 1) {
            userDao.deleteUser(user);
            return true;
        } else {
            if (loginUser.getGroupsByGroupId().getId() == 1 && user.getGroupsByGroupId().getId() > 1) {
                userDao.deleteUser(user);
                return true;
            } else if (loginUser.getGroupsByGroupId().getId() == 2 && owner.getId() == loginUser.getId()) {
                userDao.deleteUser(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public Users getUser(Integer id) {
        return userDao.getUser(id);
    }

    @Override
    public List<Users> getAddGroupUsers(int gid) {
        return userDao.getAddGroupUsers(gid);
    }

    public void test() {
        Users user = (Users) request.getSession().getAttribute("loginUser");
        System.out.println(user);
    }
}
