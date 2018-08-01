package demo.service;

import demo.entities.Users;

import java.util.List;
import java.util.Map;

public interface UserService {
    public Map<String, Object> getAccessibilityUsers(Integer uid);

    public void saveUser(Users user);

    public void updateUser(Users user);

    public void deleteUser(Integer id);

    public Users getUser(Integer id);

    public List<Object[]> getAddGroupUsers(int gid);
}

