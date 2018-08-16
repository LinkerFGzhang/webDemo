package demo.repository;

import demo.entities.Users;

import java.util.List;

public interface UserDao {
    public List<Users> getAccessibilityUsers(String hql);

    public void saveUser(Users user);

    public void updateUser(Users user);

    public void deleteUser(Users user);

    public Users getUser(Integer id);

    public List<Users> getAddGroupUsers(int gid);

    public Users getOwner(int gid);

    public Users test();
}
