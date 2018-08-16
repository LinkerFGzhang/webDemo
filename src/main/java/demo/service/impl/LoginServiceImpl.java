package demo.service.impl;

import demo.entities.Groups;
import demo.entities.Users;
import demo.repository.LoginDao;
import demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    @Override
    @Transactional(readOnly = true)
    public Users login(String username, String password) {
        return loginDao.login(username, password);
    }

}
