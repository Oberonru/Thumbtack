package net.thumbtack.school.server.dao;

import net.thumbtack.school.server.model.User;

import java.util.List;

public interface UserDao {
    void insert(User user);
    void updateUser(User user);
    List<User> getUserList();
}
