package demo.service;

import demo.entities.Users;

public interface LoginService {
    public Users login(String username, String password);
}