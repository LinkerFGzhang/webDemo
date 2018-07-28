package demo.service.impl;

import demo.entities.Users;
import demo.repository.UserDao;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userMenuDao;

    @Override
    public Map<String, Object> getAllUser(Integer uid) {
        return userMenuDao.getAllUser(uid);
    }

    @Override
    public void saveUser(Users user) {
        userMenuDao.saveUser(user);
    }

    @Override
    public void updateUser(Users user) {
        userMenuDao.updateUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userMenuDao.deleteUser(id);
    }

    @Override
    public Users getUser(Integer id) {
        return userMenuDao.getUser(id);
    }
}
