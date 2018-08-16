package demo.service;

import demo.entities.Users;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserService {
    public List<Users> getAccessibilityUsers();

    public boolean saveUser(Users user);

    public boolean updateUser(Users user);

    public boolean deleteUser(Integer id);

    public Users getUser(Integer id);

    public List<Users> getAddGroupUsers(int gid);

    public void test();
}

