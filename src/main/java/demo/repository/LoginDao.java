package demo.repository;

import demo.entities.Users;

public interface LoginDao {

    public Users login(String username, String password);
}