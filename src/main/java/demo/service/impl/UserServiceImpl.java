package demo.service.impl;

import demo.entities.Users;
import demo.repository.UserDao;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Map<String, Object> getAccessibilityUsers(Integer uid) {
        return userDao.getAccessibilityUsers(uid);
    }

    @Override
    public void saveUser(Users user) {
        userDao.saveUser(user);
    }

    @Override
    public void updateUser(Users user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userDao.deleteUser(id);
    }

    @Override
    public Users getUser(Integer id) {
        return userDao.getUser(id);
    }

    @Override
    public List<Object[]> getAddGroupUsers(int gid) {
        return userDao.getAddGroupUsers(gid);
    }
}
