package demo.service;

import demo.entities.Users;

import java.util.Map;

public interface UserService {
    public Map<String, Object> getAllUser(Integer uid);

    public void saveUser(Users user);

    public void updateUser(Users user);

    public void deleteUser(Integer id);

    public Users getUser(Integer id);
}

